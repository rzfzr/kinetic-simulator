unit Unit2;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls,math;

type
  TForm2 = class(TForm)
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Edit1: TEdit;
    Edit2: TEdit;
    Edit3: TEdit;
    Edit4: TEdit;
    Button1: TButton;
    Label7: TLabel;
    Label8: TLabel;
    procedure Button1Click(Sender: TObject);
    procedure Edit1Change(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form2: TForm2;
  MeiaVidaCalculada:Extended;

implementation

{$R *.dfm}

procedure TForm2.Button1Click(Sender: TObject);
var
jogini,jogfin,dadoini,dadofin:Integer;
begin

jogini:=strtoint(edit1.Text);
jogfin:=strtoint(edit2.Text);
dadoini:=strtoint(edit3.Text);
dadofin:=strtoint(edit4.Text);

MeiaVidaCalculada:=((jogfin-jogini)*log10(2))/(log10(dadoini/dadofin));

Label8.Caption:=floattostr(MeiaVidaCalculada);



end;

procedure TForm2.Edit1Change(Sender: TObject);
begin

end;

end.
