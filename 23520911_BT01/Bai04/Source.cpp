#include<iostream>
using namespace std;
struct PHANSO
{
	int TuSo;
	int MauSo;
};

void Nhap(PHANSO&);
void Xuat(PHANSO);
void RutGon(PHANSO&);
PHANSO Tong(PHANSO, PHANSO);
PHANSO Hieu(PHANSO, PHANSO);
PHANSO Tich(PHANSO, PHANSO);
PHANSO Thuong(PHANSO, PHANSO);

int main()
{
	PHANSO ps1, ps2;
	cout << "Nhap phan so thu nhat: " << endl;
	Nhap(ps1);
	cout << "Nhap phan so thu hai: " << endl;
	Nhap(ps2);
	PHANSO tong = Tong(ps1, ps2);
	PHANSO hieu = Hieu(ps1, ps2);
	PHANSO tich = Tich(ps1, ps2);
	PHANSO thuong = Thuong(ps1, ps2);
	RutGon(tong);
	RutGon(hieu);
	RutGon(tich);
	RutGon(thuong);
	Xuat(ps1);
	Xuat(ps2);
	cout << "Tong = ";
	Xuat(tong);
	cout << "Hieu = ";
	Xuat(hieu);
	cout << "Tich = ";
	Xuat(tich);
	cout << "Thuong = ";
	Xuat(thuong);
	return 1;
}
void Nhap(PHANSO& ps)
{
	cout << "Nhap tu so: ";
	cin >> ps.TuSo;
	cout << "Nhap mau so: ";
	cin >> ps.MauSo;
}
void Xuat(PHANSO ps)
{
	cout << ps.TuSo << "/" << ps.MauSo << "\n";
}
void RutGon(PHANSO& ps)
{
	int a = abs(ps.TuSo);
	int b = abs(ps.MauSo);
	while (a * b != 0)
	{
		if (a > b)
			a = a - b;
		else
			b = b - a;
	}
	ps.TuSo = ps.TuSo / (a + b);
	ps.MauSo = ps.MauSo / (a + b);
}

PHANSO Tong(PHANSO ps1, PHANSO ps2)
{
	PHANSO temp;
	temp.TuSo = ps1.TuSo * ps2.MauSo + ps2.TuSo * ps1.MauSo;
	temp.MauSo = ps1.MauSo * ps2.MauSo;
	return temp;
}

PHANSO Hieu(PHANSO ps1, PHANSO ps2)
{
	PHANSO temp;
	temp.TuSo = ps1.TuSo * ps2.MauSo - ps2.TuSo * ps1.MauSo;
	temp.MauSo = ps1.MauSo * ps2.MauSo;
	return temp;
}

PHANSO Tich(PHANSO ps1, PHANSO ps2)
{
	PHANSO temp;
	temp.TuSo = ps1.TuSo * ps2.TuSo;
	temp.MauSo = ps1.MauSo * ps2.MauSo;
	return temp;
}

PHANSO Thuong(PHANSO ps1, PHANSO ps2)
{
	PHANSO temp;
	temp.TuSo = ps1.TuSo * ps2.MauSo;
	temp.MauSo = ps1.MauSo * ps2.TuSo;
	return temp;
}