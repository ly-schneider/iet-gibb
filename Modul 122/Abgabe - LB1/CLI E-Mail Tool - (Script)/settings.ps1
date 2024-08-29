Write-Host
Write-Host "Bitte wählen Sie eine Option:"
Write-Host "1 (Sender-E-Mail-Adresse ändern)"
Write-Host "2 (Konfigurierte Sender-E-Mail-Adresse anzeigen)" 
Write-Host "3 (Gmail App Code ändern)"
Write-Host "4 (Konfigurierten Gmail App Code anzeigen)"
Write-Host "5 (Zurück)"
Write-Host "6 (Exit)"
Write-Host

$option = Read-Host "Option wählen"

switch ($option) {
  1 { 
    $Content = Get-Content .\settings.txt
    $Email = $null

    # Repeat until the user types a valid email address
    while ($null -eq $Email) {
      $Email = Read-Host "Bitte geben Sie eine neue Sender-E-Mail-Adresse ein"
      if ($Email -match "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" -and $Email -like "*@gmail.com") {
        $NewContent = $Email + "`n" + $Content[1]
        $NewContent | Out-File .\settings.txt
        Write-Host ""
        Write-Host "E-Mail-Adresse erfolgreich geändert! Ihre neue E-Mail-Adresse ist: $Email"
        .\settings.ps1
      }
      else {
        Write-Host ""
        Write-Host "Diese E-Mail-Adresse ist ungültig. Bitte geben Sie eine gültige E-Mail-Adresse ein."
        $Email = $null
      }
    }
  }
  2 { 
    $Content = Get-Content .\settings.txt
    $From = $Content[0]

    Write-Host ""
    Write-Host "Ihre konfigurierte Sender-E-Mail-Adresse ist: $From"
    .\settings.ps1
  }
  3 { 
    $Content = Get-Content .\settings.txt
    $Password = $null

    # Repeat until the user types a valid Gmail App Code
    while ($null -eq $Password) {
      $Password = Read-Host "Bitte geben Sie einen neuen Gmail App-Code ein"

      if ($Password -ne "") {
        $NewContent = $Content[0] + "`n" + $Password
        $NewContent | Out-File .\settings.txt
        Write-Host ""
        Write-Host "Gmail App-Code erfolgreich geändert!"
        .\settings.ps1
      }
      else {
        Write-Host ""
        Write-Host "Der Gmail App-Code darf nicht leer sein. Bitte geben Sie einen gültigen Gmail App-Code ein."
        $Password = $null
      }
    }
  }
  4 { 
    $Content = Get-Content .\settings.txt
    $Password = $Content[1]

    Write-Host ""
    Write-Host "Ihr konfigurierter Gmail App-Code ist: $Password"
    .\settings.ps1
  }
  5 { .\script.ps1 }
  6 { exit }
  default { Write-Host "Ungültige Option" }
}