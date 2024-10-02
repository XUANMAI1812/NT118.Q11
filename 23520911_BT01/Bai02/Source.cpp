#include<iostream>
#include<cmath>
using namespace std;

struct DIEM
{
	float x;
	float y;
	float z;
};

void Nhap(DIEM&);
void Xuat(DIEM);
float KhoangCach(DIEM, DIEM);
int main()
{
	DIEM A, B;
	cout << "Nhap diem thu nhat: " << endl;
	Nhap(A);
	cout << "Nhap diem thu hai: " << endl;
	Nhap(B);
	float kc = KhoangCach(A, B);
	Xuat(A);
	Xuat(B);
	cout << "Khoang cach giua hai diem: " << kc;
	return 1;
}
void Nhap(DIEM& P)
{
	cout << "Nhap x: ";
	cin >> P.x;
	cout << "Nhap y: ";
	cin >> P.y;
	cout << "Nhap z: ";
	cin >> P.z;
}
void Xuat(DIEM P)
{
	cout << "(" << P.x << ", " << P.y << ", " << P.z << ")" << endl;
}
float KhoangCach(DIEM A, DIEM B)
{
	float kc = sqrt(pow(B.x - A.x, 2) + pow(B.y - A.y, 2) + pow(B.z - A.z, 2));
	return kc;
}