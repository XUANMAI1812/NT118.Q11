#include <iostream>
using namespace std;
struct ThoiGian
{
	int Ngay;
	int Thang;
	int Nam;
};

void Nhap(ThoiGian&);
void Xuat(ThoiGian);
int KiemTraNamNhuan(ThoiGian);
long SoThuTu(ThoiGian);
int STTTrongNam(ThoiGian);
ThoiGian TimNgay(long);
ThoiGian TimNgay(int, int);
ThoiGian TimNgayTruoc(ThoiGian);

int main()
{
	ThoiGian x;
	Nhap(x);
	Xuat(x);
	ThoiGian kq = TimNgayTruoc(x);
	cout << "Ngay truoc: ";
	Xuat(kq);
	return 1;
}

void Nhap(ThoiGian& x)
{
	cout << "Nhap ngay: ";
	cin >> x.Ngay;
	cout << "Nhap thang: ";
	cin >> x.Thang;
	cout << "Nhap nam: ";
	cin >> x.Nam;
}
void Xuat(ThoiGian x)
{
	cout << x.Ngay << "/" << x.Thang << "/" << x.Nam << endl;
}
int KiemTraNamNhuan(ThoiGian x)
{
	if (x.Nam % 4 == 0 && x.Nam % 100 != 0)
		return 1;
	if (x.Nam % 400 == 0)
		return 1;
	return 0;
}
long SoThuTu(ThoiGian x)
{
	int nam = 1;
	int songay = 365;
	long stt = 0;
	for (int i = 1; i < x.Nam; i++)
	{
		stt = stt + 365;
		ThoiGian temp = { 1, 1, i };
		if (KiemTraNamNhuan(temp) == 1)
			stt = stt + 1;
	}
	return stt + STTTrongNam(x);
}
int STTTrongNam(ThoiGian x)
{
	int ngaythang[12] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	if (KiemTraNamNhuan(x) == 1)
		ngaythang[1] = 29;
	int stt = 0;
	for (int i = 1; i < x.Thang; i++)
	{
		stt = stt + ngaythang[i - 1];
	}
	return stt + x.Ngay;
}
ThoiGian TimNgay(long stt)
{
	int nam = 1;
	int songay = 365;
	while (stt - songay > 0)
	{
		stt = stt - songay;
		nam++;
		ThoiGian temp = { 1, 1, nam };
		if (KiemTraNamNhuan(temp) == 1)
			songay = 366;
		else
			songay = 365;
	}
	return TimNgay(nam, stt);
}
ThoiGian TimNgay(int nam, int stt)
{
	int ngaythang[12] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	ThoiGian x = { 1, 1, nam };
	if (KiemTraNamNhuan(x) == 1)
		ngaythang[1] = 29;
	x.Thang = 1;
	while (stt - ngaythang[x.Thang - 1] > 0)
	{
		stt = stt - ngaythang[x.Thang - 1];
		x.Thang++;
	}
	x.Ngay = stt;
	return x;
}
ThoiGian TimNgayTruoc(ThoiGian x)
{
	long stt = SoThuTu(x);
	stt--;
	return TimNgay(stt);
}