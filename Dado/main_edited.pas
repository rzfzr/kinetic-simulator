var
  Form1: TForm1;
  dado: array of integer;
  dadoAux: array of integer;
  numeroDados,numeroFaces,aux: integer;
  k:Integer;
  jogada:Integer;
  rand:Integer;
  jaPassouPrimeiraRodada:boolean;
  numeroDado,p:Integer;
  cont:integer;
  meiaVida:Extended;

implementation

begin
jogada:=-1;
numeroDados := strtoint(edit1.text);
numeroFaces := strtoint(txtOutroValor.text);
numeroDado:=numeroDados;
while(numeroDado >=0) do
begin
if ( jaPassouPrimeiraRodada=true ) then
begin
 jaPassouPrimeiraRodada:=false;
 Randomize;
 jogada:=jogada+1;
 SetLength(dado,numeroDados);
 numeroDado:=high(dado);
 for i := 0 to  high(dado) do
  begin
    aux := random(numeroFaces);
    if(aux = 0) then
    aux := numeroFaces;
    dado[i]:= aux;
   end;

  Randomize;
  rand := random(numeroFaces);
  if(rand = 0) then
   rand := numeroFaces;	
  SetLength(dadoAux,high(dado));
  numeroDado:=high(dadoAux);
  k:=0;
  for i:=0 to high(dado) do
  begin
   if(dado[i] <> rand) then
   begin
    dadoAux[k] := dado[i];
    k:=k+1;
   end;
  end;

  Chart1.Series[0].AddXY ( jogada, high(dadoAux)+2, '', clteecolor );
  memo1.Lines.Add(inttostr(cont) +#9+ inttostr(high(dadoAux)+2));
  ListBox1.Items.Add(inttostr(cont)+#9+'   '^I'   '+#9+inttostr(high(dadoAux)+2));
  Button4.Visible := True;


end;

if ( jaPassouPrimeiraRodada = false ) then
begin
jogada:=jogada+1;
Randomize;
SetLength(dadoAux,k);
numeroDado:=high(dadoAux);
for i := 0 to high(dadoAux) do
  begin
    aux := random(numeroFaces);
    if(aux = 0) then
    aux := numeroFaces;
    dadoAux[i]:= aux;
  end;
 k:=0;

 rand := random(numeroFaces);
 if(rand = 0) then
  rand := numeroFaces;
 for i:=0 to high(dadoAux) do
 begin
  if(dadoAux[i] <> rand) then
  begin
   dadoAux[k] := dadoAux[i];
   k:=k+1;
  end;
 end;
 cont:=cont+1;
 Chart1.Series[0].AddXY ( jogada, high(dadoAux), '', clteecolor );
 memo1.Lines.Add(inttostr(cont)+ #9 + inttostr(high(dadoAux)));
 ListBox1.Items.Add(inttostr(cont)+#9+'   '^I'   '+#9+inttostr(high(dadoAux))) ;
  if(high(dadoAux) < 1) then
  exit;

 SetLength(dadoAux,k);
 numeroDado:=high(dadoAux);
end;
end;
end;

procedure TForm1.FormCreate(Sender: TObject);
begin
memo1.Lines.Add('Decaimento');
memo1.Lines.Add('jogadaadas'+#9+'No.Dados');
cont:=0;
jaPassouPrimeiraRodada:=true;
jogada :=-1;
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
memo1.Clear;
memo1.Lines.Add('Decaimento');
memo1.Lines.Add('jogadaadas'+#9+'No.Dados');
Chart1.Series[0].Clear;
cont:=0;
dado:=nil;
dadoAux:=nil;
  numeroDados:=strtoint(edit1.Text);
  numeroFaces:=strtoint(edit1.Text);
  aux:=0;
  k:=0;
  jogada:=0;
  rand:=0;
  jaPassouPrimeiraRodada:=true;
  numeroDado:=numeroDados;
  p:=0;
  Button4.Visible:=False;
end;
