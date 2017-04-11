unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ExtCtrls, TeeProcs, TeEngine, Chart, StdCtrls, Series, Menus,
  jpeg, RpDefine, RpRender, RpRenderText, pngextra,math;

type
  TForm1 = class(TForm)
    Button1: TButton;
    Chart1: TChart;
    Edit1: TEdit;
    txtOutroValor: TEdit;
    Series1: TFastLineSeries;
    Label1: TLabel;
    Label2: TLabel;
    Button2: TButton;
    CheckBox1: TCheckBox;
    CheckBox2: TCheckBox;
    CheckBox3: TCheckBox;
    CheckBox4: TCheckBox;
    SaveDialog1: TSaveDialog;
    Memo1: TMemo;
    Button3: TButton;
    PNGButton1: TPNGButton;
    PNGButton2: TPNGButton;
    PNGButton3: TPNGButton;
    PNGButton4: TPNGButton;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Button4: TButton;
    ListBox1: TListBox;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    procedure Button1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure CheckBox1Click(Sender: TObject);
    procedure CheckBox2Click(Sender: TObject);
    procedure CheckBox3Click(Sender: TObject);
    procedure CheckBox4Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure txtOutroValorChange(Sender: TObject);
    procedure Button4Click(Sender: TObject);
    procedure PNGButton1Click(Sender: TObject);
    procedure PNGButton2Click(Sender: TObject);
    procedure PNGButton3Click(Sender: TObject);
    procedure PNGButton4Click(Sender: TObject);
    procedure ListBox1MouseDown(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure Label5Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;
  dado: array of integer;
  dadoAux: array of integer;
  nd,nf,aux: integer;
  k:Integer;
  jog:Integer;
  rand:Integer;
  b:boolean;
  ndado,p:Integer;
  cont:integer;
  meiavida:Extended;

implementation

uses Unit2;

{$R *.dfm}

procedure TForm1.Button1Click(Sender: TObject);
var
i :integer;


begin
jog:=-1;
nd := strtoint(edit1.text);
nf := strtoint(txtOutroValor.text);
ndado:=nd;
while(ndado >=0) do
begin
if ( b=true ) then
begin
 b:=false;
 Randomize;
 jog:=jog+1;
 SetLength(dado,nd);
 ndado:=high(dado);
 for i := 0 to  high(dado) do
  begin
    aux := random(nf);
    if(aux = 0) then
    aux := nf;
    dado[i]:= aux;
   end;

  Randomize;
  rand := random(nf);
  if(rand = 0) then
   rand := nf;
  SetLength(dadoAux,high(dado));
  ndado:=high(dadoAux);
  k:=0;
  for i:=0 to high(dado) do
  begin
   if(dado[i] <> rand) then
   begin
    dadoAux[k] := dado[i];
    k:=k+1;
   end;
  end;

  Chart1.Series[0].AddXY ( jog, high(dadoAux)+2, '', clteecolor );
  memo1.Lines.Add(inttostr(cont) +#9+ inttostr(high(dadoAux)+2));
  ListBox1.Items.Add(inttostr(cont)+#9+'   '^I'   '+#9+inttostr(high(dadoAux)+2));
  Button4.Visible := True;


end;

if ( b = false ) then
begin
jog:=jog+1;
Randomize;
SetLength(dadoAux,k);
ndado:=high(dadoAux);
for i := 0 to high(dadoAux) do
  begin
    aux := random(nf);
    if(aux = 0) then
    aux := nf;
    dadoAux[i]:= aux;
  end;
 k:=0;

 rand := random(nf);
 if(rand = 0) then
  rand := nf;
 for i:=0 to high(dadoAux) do
 begin
  if(dadoAux[i] <> rand) then
  begin
   dadoAux[k] := dadoAux[i];
   k:=k+1;
  end;
 end;
 cont:=cont+1;
 Chart1.Series[0].AddXY ( jog, high(dadoAux), '', clteecolor );
 memo1.Lines.Add(inttostr(cont)+ #9 + inttostr(high(dadoAux)));
 ListBox1.Items.Add(inttostr(cont)+#9+'   '^I'   '+#9+inttostr(high(dadoAux))) ;
  if(high(dadoAux) < 1) then
  exit;

 {showmessage('removido: '+ inttostr(rand));
 for f:=0 to k-1 do
  showmessage(inttostr(dadoAux[f]));
  }
 SetLength(dadoAux,k);
 ndado:=high(dadoAux);
end;
end;
end;

procedure TForm1.FormCreate(Sender: TObject);
begin
memo1.Lines.Add('Decaimento');
memo1.Lines.Add('Jogadas'+#9+'No.Dados');
cont:=0;
b:=true;
jog :=-1;
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
memo1.Clear;
memo1.Lines.Add('Decaimento');
memo1.Lines.Add('Jogadas'+#9+'No.Dados');
Chart1.Series[0].Clear;
cont:=0;
dado:=nil;
dadoAux:=nil;
  nd:=strtoint(edit1.Text);
  nf:=strtoint(edit1.Text);
  aux:=0;
  k:=0;
  jog:=0;
  rand:=0;
  b:=true;
  ndado:=nd;
  p:=0;
  Button4.Visible:=False;
end;

procedure TForm1.CheckBox1Click(Sender: TObject);
begin
if (CheckBox1.Checked) then
begin
  txtOutroValor.Text:='3';
  CheckBox2.Checked := false;
  CheckBox3.Checked := false;
  CheckBox4.Checked := false;
end;
end;

procedure TForm1.CheckBox2Click(Sender: TObject);
begin
if (CheckBox2.Checked) then
begin
  txtOutroValor.Text:='4';
  CheckBox1.Checked := false;
  CheckBox3.Checked := false;
  CheckBox4.Checked := false;
end;
end;

procedure TForm1.CheckBox3Click(Sender: TObject);
begin
if (CheckBox3.Checked) then
begin
  txtOutroValor.Text:='5';
  CheckBox1.Checked := false;
  CheckBox2.Checked := false;
  CheckBox4.Checked := false;
end;
end;

procedure TForm1.CheckBox4Click(Sender: TObject);
begin
if (CheckBox4.Checked) then
begin
  txtOutroValor.Text:='6';
  CheckBox1.Checked := false;
  CheckBox2.Checked := false;
  CheckBox3.Checked := false;
end;
end;

procedure TForm1.Button3Click(Sender: TObject);
begin
if savedialog1.Execute then
   begin
   memo1.Lines.SaveToFile(savedialog1.FileName);
   memo1.SetFocus;
   end;
end;



procedure TForm1.txtOutroValorChange(Sender: TObject);
var ctrl:Extended;
begin
if (txtOutroValor.Text = '0') or (txtOutroValor.Text = '1') or (txtOutroValor.Text = '00') then
exit;
ctrl := strtoint(txtOutroValor.Text)/(strtoint(txtOutroValor.text)-1);
meiavida:= log10(2) / log10(ctrl);
label10.Caption := floattostr(meiavida);

end;

procedure TForm1.Button4Click(Sender: TObject);
begin
//If(Form2.Edit1.Text = '') or (Form2.Edit2.Text = '') or (Form2.Edit3.Text = '') or (Form2.Edit3.Text = '') Then
  //Begin
    //ShowMessage('Você precisa selecionar dois valores');
  //End
//Else
  Form2.Show;
end;

procedure TForm1.PNGButton1Click(Sender: TObject);
begin
CheckBox1.Checked:=True;
CheckBox2.Checked:=False;
CheckBox3.Checked:=False;
CheckBox4.Checked:=False;
end;

procedure TForm1.PNGButton2Click(Sender: TObject);
begin
CheckBox1.Checked:=False;
CheckBox2.Checked:=True;
CheckBox3.Checked:=False;
CheckBox4.Checked:=False;
end;

procedure TForm1.PNGButton3Click(Sender: TObject);
begin
CheckBox1.Checked:=False;
CheckBox2.Checked:=False;
CheckBox3.Checked:=True;
CheckBox4.Checked:=False;
end;

procedure TForm1.PNGButton4Click(Sender: TObject);
begin
CheckBox1.Checked:=False;
CheckBox2.Checked:=False;
CheckBox3.Checked:=False;
CheckBox4.Checked:=True;
end;


procedure TForm1.Label5Click(Sender: TObject);
begin

end;

procedure TForm1.ListBox1MouseDown(Sender: TObject; Button: TMouseButton;
  Shift: TShiftState; X, Y: Integer);
var
  lista : TStringList;
  aux : string;
begin
  lista := TStringList.Create;
  aux := ListBox1.Items[ListBox1.ItemIndex];
  ExtractStrings([' '],[' '],aux,lista);
  if(Button = mbLeft) then // se foi o botão esquerdo
    begin
      Form2.Edit1.Text := ListBox1.Items[ListBox1.ItemIndex];
      Form2.Edit3.Text := ListBox1.Items[ListBox1.ItemIndex];
    end;
end;

end.
