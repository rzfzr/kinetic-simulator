object Form1: TForm1
  Left = 199
  Top = 125
  BorderIcons = [biSystemMenu, biMinimize]
  Caption = 'DADO'
  ClientHeight = 565
  ClientWidth = 778
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 8
    Top = 48
    Width = 37
    Height = 13
    Caption = 'Dados'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object Label2: TLabel
    Left = 0
    Top = 464
    Width = 97
    Height = 17
    Caption = 'Outros Valores'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -15
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label3: TLabel
    Left = 88
    Top = 56
    Width = 62
    Height = 17
    Caption = 'Meia Vida'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -15
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label4: TLabel
    Left = 88
    Top = 80
    Width = 71
    Height = 17
    Caption = 'Te'#243'rica (P)'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -15
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label5: TLabel
    Left = 96
    Top = 120
    Width = 57
    Height = 16
    Caption = 'P = 1,709'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label6: TLabel
    Left = 96
    Top = 296
    Width = 57
    Height = 16
    Caption = 'P = 3,106'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label7: TLabel
    Left = 96
    Top = 200
    Width = 57
    Height = 16
    Caption = 'P = 2,409'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label8: TLabel
    Left = 96
    Top = 376
    Width = 57
    Height = 16
    Caption = 'P = 3,802'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label9: TLabel
    Left = 88
    Top = 496
    Width = 21
    Height = 16
    Caption = 'P ='
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
  end
  object Label10: TLabel
    Left = 104
    Top = 496
    Width = 52
    Height = 16
    Caption = '             '
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
    OnClick = Label10Click
  end
  object Label11: TLabel
    Left = 672
    Top = 8
    Width = 57
    Height = 13
    Caption = 'Decaimento'
  end
  object Label12: TLabel
    Left = 672
    Top = 24
    Width = 40
    Height = 13
    Caption = 'Jogadas'
  end
  object Label13: TLabel
    Left = 728
    Top = 24
    Width = 46
    Height = 13
    Caption = 'N'#186' Dados'
  end
  object Button1: TButton
    Left = 8
    Top = 16
    Width = 75
    Height = 25
    Caption = 'JOGAR'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = [fsBold]
    ParentFont = False
    TabOrder = 0
    OnClick = Button1Click
  end
  object Edit1: TEdit
    Left = 8
    Top = 72
    Width = 57
    Height = 23
    Hint = 'Digite aqui o n'#250'mero de dados'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -12
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
    TabOrder = 1
    Text = '300000'
  end
  object txtOutroValor: TEdit
    Left = 16
    Top = 488
    Width = 33
    Height = 24
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
    TabOrder = 2
    Text = '6'
    OnChange = txtOutroValorChange
  end
  object Button2: TButton
    Left = 96
    Top = 24
    Width = 49
    Height = 17
    Caption = 'Limpar'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = [fsBold]
    ParentFont = False
    TabOrder = 3
    OnClick = Button2Click
  end
  object CheckBox1: TCheckBox
    Left = 8
    Top = 160
    Width = 65
    Height = 17
    Caption = '3 Faces'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
    TabOrder = 4
    OnClick = CheckBox1Click
  end
  object CheckBox2: TCheckBox
    Left = 8
    Top = 240
    Width = 65
    Height = 17
    Caption = '4 Faces'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
    TabOrder = 5
    OnClick = CheckBox2Click
  end
  object CheckBox3: TCheckBox
    Left = 8
    Top = 328
    Width = 65
    Height = 17
    Caption = '5 Faces'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
    TabOrder = 6
    OnClick = CheckBox3Click
  end
  object CheckBox4: TCheckBox
    Left = 8
    Top = 432
    Width = 65
    Height = 17
    Caption = '6 Faces'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
    TabOrder = 7
    OnClick = CheckBox4Click
  end
  object Memo1: TMemo
    Left = 192
    Top = 512
    Width = 105
    Height = 49
    TabOrder = 8
    Visible = False
    WantTabs = True
  end
  object Button3: TButton
    Left = 672
    Top = 448
    Width = 105
    Height = 25
    Hint = 'Salvar informa'#231#245'es'
    Caption = 'Salvar'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -12
    Font.Name = 'Myriad Web Pro'
    Font.Style = [fsBold]
    ParentFont = False
    TabOrder = 9
    OnClick = Button3Click
  end
  object Button4: TButton
    Left = 464
    Top = 72
    Width = 129
    Height = 33
    Caption = 'Meia Vida Calculada'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Myriad Web Pro'
    Font.Style = []
    ParentFont = False
    TabOrder = 11
    Visible = False
    OnClick = Button4Click
  end
  object ListBox1: TListBox
    Left = 672
    Top = 40
    Width = 113
    Height = 401
    ItemHeight = 13
    TabOrder = 10
    TabWidth = 2
    OnMouseDown = ListBox1MouseDown
  end
  object SaveDialog1: TSaveDialog
    Left = 1056
    Top = 488
  end
end
