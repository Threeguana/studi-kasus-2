import java.util.Scanner;

public class SistemManajemenKafe {
    static Scanner sc = new Scanner(System.in);

    static String[][] daftarMenu = {
            {"Kopi Hitam", "15000"},
            {"Latte     ", "22000"},
            {"Teh Tarik ", "15000"},
            {"Mie Goreng", "18000"},
            {"Americano ", "22000"}
    };

    static String[][] pesanan = new String[100][5];
    static int jmlPesanan = 0;

    public static void tampilkanDaftarMenu() {
        System.out.println("\n====== DAFTAR MENU ======");
        for (int i = 0; i < daftarMenu.length; i++) {
            System.out.printf("%d. %s - Rp %s\n", i + 1, daftarMenu[i][0], daftarMenu[i][1]);
        }
    }

    public static void tambahPesanan() {
        System.out.print("\nMasukkan nama pelanggan: ");
        String namaPelanggan = sc.nextLine();
        System.out.print("Masukkan nomor meja: ");
        int nomorMeja = Integer.parseInt(sc.nextLine());

        tampilkanDaftarMenu();

        double totHarga = 0;

        while (true) {
            System.out.print("\nPilih menu (masukkan nomor menu, atau 0 untuk selesai): ");
            int pilihanMenu = sc.nextInt();
            sc.nextLine();

            if (pilihanMenu == 0) {
                System.out.println("\nPesanan berhasil ditambahkan.");
                System.out.printf("Total Harga Pesanan: Rp %.0f\n", totHarga);
                break;
            }

            if (pilihanMenu < 1 || pilihanMenu > daftarMenu.length) {
                System.out.println("Pilihan menu tidak valid. Silakan coba lagi.");
                continue;
            }

            String namaMenu = daftarMenu[pilihanMenu - 1][0];
            double hargaMenu = Double.parseDouble(daftarMenu[pilihanMenu - 1][1]);

            System.out.print("Masukkan jumlah item untuk " + namaMenu + ": ");
            int jmlItem = sc.nextInt();
            sc.nextLine();

            if (jmlItem <= 0) {
                System.out.println("Jumlah item harus lebih besar dari 0.");
                continue;
            }

            double totHargaItem = hargaMenu * jmlItem;
            totHarga += totHargaItem;

            if (jmlPesanan >= 100) {
                System.out.println("Kapasitas pesanan maksimal telah tercapai.");
                break;
            }

            pesanan[jmlPesanan][0] = namaPelanggan;
            pesanan[jmlPesanan][1] = Integer.toString(nomorMeja);
            pesanan[jmlPesanan][2] = namaMenu;
            pesanan[jmlPesanan][3] = Integer.toString(jmlItem);
            pesanan[jmlPesanan][4] = String.format("%.0f", totHargaItem);
            jmlPesanan++;
        }
    }

    public static void tampilkanDaftarPesanan() {
        if (jmlPesanan == 0) {
            System.out.println("\nBelum ada pesanan.");
            return;
        }

        System.out.println("\n======= DAFTAR PESANAN =======");

        boolean[] sdhTampil = new boolean[jmlPesanan];

        for (int i = 0; i < jmlPesanan; i++) {
            if (!sdhTampil[i]) {
                System.out.println("\nNama Pelanggan: " + pesanan[i][0]);
                System.out.println("Nomor Meja: " + pesanan[i][1]);
                System.out.println("Detail Pesanan:");
                double totalHargaPelanggan = 0;

                for (int j = i; j < jmlPesanan; j++) {
                    if (pesanan[j][0].equals(pesanan[i][0]) && pesanan[j][1].equals(pesanan[i][1])) {
                        System.out.printf("- %s x %s Rp %s\n",
                                pesanan[j][2],
                                pesanan[j][3],
                                pesanan[j][4]);
                        totalHargaPelanggan += Double.parseDouble(pesanan[j][4]);
                        sdhTampil[j] = true;
                    }
                }
                System.out.printf("Total Harga Pesanan: Rp %.0f\n", totalHargaPelanggan);
                System.out.println("--------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n======== MENU UTAMA ========");
            System.out.println("1. Tambahkan Pesanan");
            System.out.println("2. Tampilkan Daftar Pesanan");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            String pilihan = sc.nextLine(); 

            switch (pilihan) {
                case "1":
                    tambahPesanan();
                    break;
                case "2":
                    tampilkanDaftarPesanan();
                    break;
                case "3":
                    System.out.println("Terima kasih. Sampai jumpa!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}
