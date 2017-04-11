object Form2: TForm2
  Left = 550
  Top = 240
  Width = 490
  Height = 334
  Caption = 'Form2'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 8
    Top = 24
    Width = 452
    Height = 18
    Caption = 
      'Escolha e copie da caixa de texto a direita os seguintes valores' +
      ':'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -16
    Font.Name = 'Myriad Web Pro'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object Label2: TLabel
    Left = 16
    Top = 72
    Width = 170
    Height = 18
    Caption = 'Numero da Jogada Inicial'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -16
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label3: TLabel
    Left = 216
    Top = 72
    Width = 230
    Height = 18
    Caption = 'N'#250'mero de dados correspondente'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -16
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label4: TLabel
    Left = 16
    Top = 136
    Width = 163
    Height = 18
    Caption = 'N'#250'mero da Jogada Final'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -16
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label5: TLabel
    Left = 216
    Top = 136
    Width = 230
    Height = 18
    Caption = 'N'#250'mero de dados correspondente'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -16
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label6: TLabel
    Left = 0
    Top = 272
    Width = 382
    Height = 15
    Caption = 
      'Nota:  As jogadas inicial e final podem ser de qualquer interval' +
      'o escolhido'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = [fsItalic]
    ParentFont = False
  end
  object Label7: TLabel
    Left = 48
    Top = 224
    Width = 118
    Height = 15
    Caption = 'Meia Vida Calculada :'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label8: TLabel
    Left = 176
    Top = 224
    Width = 17
    Height = 20
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -16
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ParentFont = False
  end
  object Edit1: TEdit
    Left = 32
    Top = 96
    Width = 81
    Height = 30
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -21
    Font.Name = 'Myriad Web Pro Condensed'
    Font.Style = []
    ParentFont = False
    TabOrder = 0
    Text = '0'
  end
  object Edit2: TEdit
    Left = 32
    Top = 160
    Width = 81
    Height = 30
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -21
    Font.Name = 'Myriad Web Pro Condensed'
    Font.Style = []
    ParentFont = False
    TabOrder = 1
    Text = '0'
  end
  object Edit3: TEdit
    Left = 232
    Top = 96
    Width = 193
    Height = 30
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -21
    Font.Name = 'Myriad Web Pro Condensed'
    Font.Style = []
    ParentFont = False
    TabOrder = 2
    Text = '0'
  end
  object Edit4: TEdit
    Left = 232
    Top = 160
    Width = 193
    Height = 30
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -21
    Font.Name = 'Myriad Web Pro Condensed'
    Font.Style = []
    ParentFont = False
    TabOrder = 3
    Text = '0'
  end
  object Button1: TButton
    Left = 336
    Top = 192
    Width = 91
    Height = 73
    Caption = 'Calcular'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -16
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
    TabOrder = 4
    OnClick = Button1Click
  end
end
