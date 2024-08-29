# Create LOG Directory if it does not exist
if (-not (Test-Path -Path ".\LOGS")) {
    New-Item -ItemType Directory -Path ".\LOGS"
}

# Infinite loop to continuously fetch emails
while ($true) {
    $Content = Get-Content .\settings.txt
    $From = $Content[0]
    $Password = $Content[1]

    # Load rss-feed
    $webclient = new-object System.Net.WebClient

    # Access the rss-feed
    $webclient.Credentials = new-object System.Net.NetworkCredential ($From, $Password)

    # Download the rss as xml
    [xml]$xml = $webclient.DownloadString("https://mail.google.com/mail/feed/atom")

    if ($null -eq $xml.feed.entry) {
        Write-Host
        Write-Host "Keine ungelesenen E-Mails vorhanden"
        Write-Host
    }
    else {
        foreach ($entry in $xml.feed.entry) {
            Write-Host
            Write-Host "Zugestellt: $($entry.issued)"
            Write-Host "E-Mail: $($entry.author.email)"
            Write-Host "Subjekt: $($entry.title)"
            Write-Host "Nachricht: $($entry.summary)"
        }
    }

    # Create a log file with the fetched emails
    $TimeStamp = Get-Date -Format "yyyy-MM-dd_HH-mm-ss"

    # Create a log file with the fetched emails
    $xml.feed.entry | ForEach-Object {
        $Log = "Zugestellt: $($_.issued)`nE-Mail: $($_.author.email)`nSubjekt: $($_.title)`nNachricht: $($_.summary)`n"
        $Log | Out-File -FilePath ".\LOGS\EmailLog_$TimeStamp.txt" -Append
    }

    Write-Host
    Write-Host "----------------------------------------"

    # Wait for 15 seconds before fetching emails again
    Start-Sleep -Seconds 15
}