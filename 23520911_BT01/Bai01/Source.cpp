#include<iostream>
using namespace std;

struct phanso
{
	float TuSo;
	float MauSo;
};

void Nhap(phanso&);
void Xuat(phanso);
int KiemTra(phanso);

int main()
{
	phanso x;
	Nhap(x);
	int KQ = KiemTra(x);
	Xuat(x);
	switch (KQ)
	{
	case 1: cout << "\nPhan so lon hon 0 ";
		break;
	case -1: cout << "\nPhan so be hon 0";
		break;
	case 0: cout << "\nPhan so bang 0";
		break;
	}
	return 1;
}
void Nhap(phanso& x)
{
	cout << "Nhap tu so: ";
	cin >> x.TuSo;
	cout << "Nhap mau so: ";
	cin >> x.MauSo;
}
void Xuat(phanso x)
{
	cout << "Tu so = " << x.TuSo << endl;
	cout << "Mau so = " << x.MauSo << endl;
}
int KiemTra(phanso x)
{
	if (x.TuSo * x.MauSo > 0)
		return 1;
	if (x.TuSo * x.MauSo < 0)
		return -1;
	return 0;
}