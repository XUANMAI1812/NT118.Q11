#include <iostream>
#include <cmath>
using namespace std;

struct TOADO 
{
    float x;
    float y;
};

struct TAMGIAC 
{
    TOADO A;
    TOADO B;
    TOADO C;
};

void Nhap(TOADO&);
void Xuat(TOADO);
void Nhap(TAMGIAC&);
void Xuat(TAMGIAC);
float KhoangCach(TOADO, TOADO);
float ChuVi(TAMGIAC);
float DienTich(TAMGIAC);
TOADO TrongTam(TAMGIAC);

int main() 
{
    TAMGIAC tg;
    Nhap(tg);
    float P = ChuVi(tg);
    float S = DienTich(tg);
    Xuat(tg);

    cout << "Chu vi la: " << P << endl;
    cout << "Dien tich la: " << S << endl;
    cout << "Toa do trong tam: ";
    Xuat(TrongTam(tg));

    return 1;
}

void Nhap(TOADO& a) {
    cout << "Nhap x: ";
    cin >> a.x;
    cout << "Nhap y: ";
    cin >> a.y;
}

void Xuat(TOADO a) {
    cout << "(" << a.x << ", " << a.y << ")" << endl;
}

float KhoangCach(TOADO a, TOADO b) {
    double KC = sqrt(pow(b.x - a.x, 2) + pow(b.y - a.y, 2));
    return KC;
}

void Nhap(TAMGIAC& Tg) {
    cout << "Nhap diem A: " << endl;
    Nhap(Tg.A);
    cout << "Nhap diem B: " << endl;
    Nhap(Tg.B);
    cout << "Nhap diem C: " << endl;
    Nhap(Tg.C);
}

void Xuat(TAMGIAC Tg) {
    cout << "diem A: ";
    Xuat(Tg.A);
    cout << "diem B: ";
    Xuat(Tg.B);
    cout << "diem C: ";
    Xuat(Tg.C);
}

float ChuVi(TAMGIAC Tg) {
    float a = KhoangCach(Tg.A, Tg.B);
    float b = KhoangCach(Tg.B, Tg.C);
    float c = KhoangCach(Tg.C, Tg.A);
    return(a + b + c);
}

float DienTich(TAMGIAC Tg) {
    float a = KhoangCach(Tg.A, Tg.B);
    float b = KhoangCach(Tg.B, Tg.C);
    float c = KhoangCach(Tg.C, Tg.A);
    float P = (a + b + c) / 2;
    return sqrt(P * (P - a) * (P - b) * (P - c));
}

TOADO TrongTam(TAMGIAC Tg) {
    TOADO TT;
    TT.x = (Tg.A.x + Tg.B.x + Tg.C.x) / 3;
    TT.y = (Tg.A.y + Tg.B.y + Tg.C.y) / 3;
    return TT;
}