$Content = Get-Content .\settings.txt
$From = $Content[0]
$Password = $Content[1]

# Check if the content is correct
if ($From -eq "" -or $Password -eq "") {
  Write-Host "E-Mail Adresse oder Gmail App-Code ist leer. Bitte konfigurieren Sie die E-Mail Adresse und das Passwort in settings.txt"
  break
}

$Password = $Password | ConvertTo-SecureString -AsPlainText -Force
$Credential = New-Object -TypeName System.Management.Automation.PSCredential -ArgumentList $From, $Password

Write-Host ""
$To = Read-Host "Gebe die E-Mail Adresse ein, an die die E-Mail gesendet werden soll"

# If email not match the regex pattern
if ($To -notmatch "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$") {
  Write-Host "Die E-Mail Adresse ist ungültig."
  .\send.ps1
}

$Subject = Read-Host "Gebe den Betreff der E-Mail ein"
if ($Subject -eq "") {
  Write-Host "Der Betreff der E-Mail darf nicht leer sein."
  .\send.ps1
}

$Body = ""
# Repeat until the user types 'SEND'
do {
  $line = Read-Host "Gebe den Inhalt der E-Mail ein (oder 'SEND' um zu senden der E-Mail)"
  if ($line -ne "SEND") {
    $Body += $line + "`n"
  }
} while ($line -ne "SEND")

if ($Body -eq "") {
  Write-Host "Der Inhalt der E-Mail darf nicht leer sein."
  .\send.ps1
}

# Function to send email
function Send-Email {
  param (
    [string]$To,
    [string]$Subject,
    [string]$Body
  )

  $EmailSettings = @{
    To         = $To
    From       = $From
    Subject    = $Subject
    Body       = $Body
    SmtpServer = "smtp.gmail.com"
    Port       = 587 
    UseSsl     = $true
    Credential = $Credential
  }

  Send-MailMessage @EmailSettings
}

Send-Email -To $To -Subject $Subject -Body $Body
Write-Host "E-Mail wurde erfolgreich gesendet!"
Write-Host

.\script.ps1