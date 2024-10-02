#include<iostream>
using namespace std;


struct PHANSO 
{
    int TuSo;
    int MauSo;
};

void Nhap(PHANSO&);
void Xuat(PHANSO);
int KiemTra(PHANSO);
PHANSO SoSanh(PHANSO, PHANSO);

int main() 
{
    PHANSO A, B;

    cout << "Nhap phan so thu nhat: " << endl;
    Nhap(A);
    cout << "Nhap phan so thu hai: " << endl;
    Nhap(B);
    PHANSO SS = SoSanh(A, B);
    Xuat(A);
    Xuat(B);
    cout << "Phan so lon nhat la: ";
    Xuat(SS);

    return 1;
}

void Nhap(PHANSO& A) 
{
    cout << "Nhap tu: ";
    cin >> A.TuSo;
    cout << "Nhap mau: ";
    cin >> A.MauSo;
}

void Xuat(PHANSO A) 
{
    cout << A.TuSo << "/" << A.MauSo << endl;
}

int KiemTra(PHANSO A)
{
    if (A.TuSo * A.MauSo > 0)
        return 1;
    if (A.TuSo * A.MauSo < 0)
        return -1;
    return 0;
}

PHANSO Hieu(PHANSO A, PHANSO B)
{
    PHANSO temp;
    temp.TuSo = A.TuSo * B.MauSo - B.TuSo * A.MauSo;
    temp.MauSo = A.MauSo * B.MauSo;
    return temp;
}

PHANSO SoSanh(PHANSO A, PHANSO B)
{
    PHANSO temp = Hieu(A, B);
    if (KiemTra(temp) == -1)
        return B;
    return A;
}