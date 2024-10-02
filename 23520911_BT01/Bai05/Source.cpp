#include <iostream>
using namespace std;

struct SoPhuc {
    float phanThuc;
    float phanAo;
};

void Nhap(SoPhuc&);
void Xuat(SoPhuc);
SoPhuc TinhTong(SoPhuc, SoPhuc);
SoPhuc TinhHieu(SoPhuc, SoPhuc);
SoPhuc TinhTich(SoPhuc, SoPhuc);

int main() {
    SoPhuc sp1, sp2;
    Nhap(sp1);
    Nhap(sp2);

    SoPhuc Tong = TinhTong(sp1, sp2);
    SoPhuc Hieu = TinhHieu(sp1, sp2);
    SoPhuc Tich = TinhTich(sp1, sp2);

    Xuat(sp1);
    Xuat(sp2);

    cout << "Tong hai so phuc: ";
    Xuat(Tong);
    cout << "Hieu hai so phuc: ";
    Xuat(Hieu);
    cout << "Tich hai so phuc: ";
    Xuat(Tich);

    return 1;
}

void Nhap(SoPhuc& sp)
{
    cout << "Nhap phan thuc: ";
    cin >> sp.phanThuc;
    cout << "Nhap phan ao: ";
    cin >> sp.phanAo;
}

void Xuat(SoPhuc sp)
{
    cout << sp.phanThuc << " + " << sp.phanAo << "i" << endl;
}

SoPhuc TinhTong(SoPhuc sp1, SoPhuc sp2) {
    SoPhuc tong;
    tong.phanThuc = sp1.phanThuc + sp2.phanThuc;
    tong.phanAo = sp1.phanAo + sp2.phanAo;
    return tong;
}

SoPhuc TinhHieu(SoPhuc sp1, SoPhuc sp2) {
    SoPhuc hieu;
    hieu.phanThuc = sp1.phanThuc - sp2.phanThuc;
    hieu.phanAo = sp1.phanAo - sp2.phanAo;
    return hieu;
}

SoPhuc TinhTich(SoPhuc sp1, SoPhuc sp2) {
    SoPhuc tich;
    tich.phanThuc = sp1.phanThuc * sp2.phanThuc - sp1.phanAo * sp2.phanAo;
    tich.phanAo = sp1.phanThuc * sp2.phanAo + sp1.phanAo * sp2.phanThuc;
    return tich;
}