# 🚀 Web Automation Framework - SauceDemo

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-FF7F00?style=for-the-badge&logo=testng&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

## 📌 Project Overview
Proyek ini adalah kerangka kerja otomatisasi pengujian (Automation Testing Framework) untuk website e-commerce **SauceDemo** (Swag Labs).

Proyek ini dibuat menggunakan **Java** dan **Selenium WebDriver**, serta menerapkan **Page Object Model (POM)** untuk memastikan kode yang rapi, mudah dibaca, dan mudah dipelihara.

**Target Website:** [https://www.saucedemo.com/](https://www.saucedemo.com/)

---

## 🧪 Test Scenarios Covered
Saya mencakup pengujian Positive, Negative, dan Security dasar.

| ID   | Test Scenario                             | Type     | Status |
|:-----|:------------------------------------------|:---------| :--- |
| TC01 | End to End Buy Test                       | Positive | ✅ Pass |
| TC02 | Login dengan password salah               | Negative | ✅ Pass |
| TC03 | Login dengan user terkunci (Locked Out)   | Negative | ✅ Pass |
| TC04 | Validasi field username & password kosong | Negative | ✅ Pass |
| TC05 | Logout Test                               | Positive | ✅ Pass |
| TC06 | Security: Back button setelah logout      | Security | ✅ Pass |

---

## 📂 Project Structure (Page Object Model)
Struktur folder dipisahkan antara **Page Objects** (Lokator elemen) dan **Test Cases** (Logika pengujian).

```text
src
└── test
    └── java
        └──pages
            ├── LoginPage.java       # Lokator & Aksi 
            ├── BurgerMenu.java  
            ├── CheckoutComplete.java  
            ├── CheckoutPage.java    
            └── InventoryPage.java
        └── tests
            ├── EndToEndBuyTest       # Happy Path Test
            └── LoginTest.java       # Kumpulan Test Case Login
```

## 🚀 How to Run (Cara Menjalankan)

Pastikan Anda sudah menginstall **Java (JDK)**, **Maven**, dan browser **Google Chrome**.

1.  **Clone repository ini:**
    ```bash
    git clone [https://github.com/ryansatrio21/SauceDemo-Automation.git](https://github.com/ryansatrio21/SauceDemo-Automation.git)
    ```
2.  **Masuk ke folder project:**
    ```bash
    cd SauceDemo-Automation
    ```
3.  **Jalankan test:**
    ```bash
    mvn clean test
    ```

