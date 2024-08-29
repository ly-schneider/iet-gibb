Write-Host "|"
Write-Host "|  Willkommen zum CLI E-Mail Tool"
Write-Host "--------------------------------------------------------------------------------------"
Write-Host "|"
Write-Host "|  © 2024 by Levyn Schneider and David Meer"
Write-Host "| (Unterstützt nur Gmail)"
Write-Host "|"

# Function to handle email and app-code input
function Get-EmailAndCode() {
  Param($text)

  # Delete settings.txt if it exists to avoid conflicts
  if (Test-Path .\settings.txt) {
    Remove-Item .\settings.txt
  }

  Write-Host "|  $text"
  $Email = $null

  # Repeat until a valid email is entered
  while ($null -eq $Email) {
    $Email = Read-Host "|  Bitte geben Sie Ihre E-Mail Adresse ein"
    if ($Email -match "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" -and $Email -like "*@gmail.com") {
      $Email | Out-File .\settings.txt

      $Password = $null

      # Repeat until a valid password is entered
      while ($null -eq $Password) {
        $Password = Read-Host "|  Bitte geben Sie Ihren Gmail App-Code ein"

        if ($Password -eq "") {
          Write-Host "|"
          Write-Host "|  Der Gmail App-Code darf nicht leer sein"
          $Password = $null
          continue
        }

        $Password | Out-File .\settings.txt -Append
        Write-Host "|"
        Write-Host "|  E-Mail Adresse und Gmail App-Code erfolgreich konfiguriert"
        Write-Host "|"
        Write-Host "--------------------------------------------------------------------------------------"
        Write-Host
      }
    }
    else {
      Write-Host "|"
      Write-Host "|  Diese E-Mail Adresse ist ungültig. Bitte geben Sie eine gültige E-Mail Adresse ein."
      $Email = $null
    }
  }
}

# Check if file exists
if (-not (Test-Path .\settings.txt)) {
  Get-EmailAndCode "Bitte geben Sie Ihre E-Mail Adresse und Gmail App Code ein um fortzufahren"
}
else {
  $Content = Get-Content .\settings.txt

  # Check if the content has two lines
  if ($Content.Count -ne 2) {
    Get-EmailAndCode "Falsche Konfiguration; Bitte geben Sie Ihre E-Mail Adresse und Gmail App Code ein um fortzufahren"
  }

  $Email = $Content[0]
  $Password = $Content[1]

  if ($Email -ne "" -and $Password -ne "") {
    if ($Email -match "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" -and $Email -like "*@gmail.com") {
      Write-Host "|  Konfigurierte Sender E-Mail Adresse: $Email"
      Write-Host "|"
      Write-Host "--------------------------------------------------------------------------------------"
      Write-Host
    }
    else {
      Get-EmailAndCode "Deine Konfigurierte E-Mail Adresse ist ungültig. Bitte gib eine gültige E-Mail Adresse ein"
    }
  }
  else {
    Get-EmailAndCode "Bitte geben Sie Ihre E-Mail Adresse und Gmail App Code ein um fortzufahren"
  }
}

# Menu Selector
Write-Host "Bitte wählen Sie eine Option:"
Write-Host "1 (E-Mail Einstellungen)"
Write-Host "2 (E-Mail Senden)"
Write-Host "3 (E-Mail Empfangen)"
Write-Host "4 (Exit)"
Write-Host

$option = Read-Host "Option wählen"

switch ($option) {
  1 { .\settings.ps1 }
  2 { .\send.ps1 }
  3 { .\receive.ps1 }
  4 { 
    Write-Host
    Write-Host "Auf Wiedersehen!"
  }
  default { Write-Host "Ungültige Option" }
}