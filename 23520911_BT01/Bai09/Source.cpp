#include <iostream>
#include <cmath>
using namespace std;

struct TOADO 
{
    int x;
    int y;
};

struct DUONGTRON 
{
    TOADO I;
    float R;
};

void Nhap(TOADO&);
void Xuat(TOADO);
void Nhap(DUONGTRON&);
void Xuat(DUONGTRON);
float ChuVi(DUONGTRON);
float DienTich(DUONGTRON);

int main()
{
    DUONGTRON a;
    Nhap(a);
    float S = DienTich(a);
    float P = ChuVi(a);
    Xuat(a);
    cout << "Dien tich la: " << S << endl;
    cout << "Chu vi la: " << P;

    return 1;
}

void Nhap(TOADO& A) 
{
    cout << "Nhap x: ";
    cin >> A.x;
    cout << "Nhap y: ";
    cin >> A.y;
}

void Xuat(TOADO A)
{
    cout << "(" << A.x << ", " << A.y << ")" << endl;
}

void Nhap(DUONGTRON& A)
{
    cout << "Nhap toa do tam: " << endl;
    Nhap(A.I);
    cout << "Nhap ban kinh: " << endl;
    cin >> A.R;
}

void Xuat(DUONGTRON A) 
{
    cout << "Toa do tam: " << endl;
    Xuat(A.I);
    cout << "Ban kinh: " << A.R << endl;
}

float DienTich(DUONGTRON A) 
{
    double S = 3.14 * (pow(A.R, 2));
    return S;
}

float ChuVi(DUONGTRON A)
{
    double P = 3.14 * 2 * A.R;
    return P;
}