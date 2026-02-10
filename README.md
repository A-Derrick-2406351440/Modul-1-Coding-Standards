# Advanced Programming EShop

# Reflection

## Module 1

### Reflection 1.1

- Dalam pengerjaan modul ini, saya telah menerapkan beberapa prinsip Clean Code dan Secure Coding.

- Pertama, saya menerapkan prinsip Single Responsibility Principle (SRP) dengan memisahkan kode ke dalam lapisan yang berbeda seperti Controller untuk menangani request HTTP, Service untuk logika bisnis, dan Repository untuk interaksi data. Penamaan variabel dan fungsi juga saya buat deskriptif, seperti createProductPost dan findAll, sehingga kode menjadi self explain tanpa perlu comment.

- Dari sisi Secure Coding dan standar web, saya tidak hanya menggunakan GET dan POST, melainkan juga memanfaatkan HTTP method yang lebih spesifik yaitu PUT untuk edit dan DELETE untuk menghapus produk, sesuai dengan prinsip RESTful. Saya juga menggunakan UUID untuk ID produk guna memastikan keunikan dan keamanan data yang lebih baik dibanding integer biasa.

- Saya juga mendapatkan pelajaran bahwa clean code dimulai dari penamaan, setiap function harus memiliki tugasnya masing-masing dan jelas, comment menjelaskan secukupnya. 

- Kekurangan yang saya temukan adalah belum adanya validasi input yang ketat (misalnya mencegah nama produk kosong di sisi server) dan belum adanya mekanisme autentikasi/otorisasi. Saat ini, siapa saja bisa menghapus atau mengedit produk.

- Untuk kedepannya error dapat di handle dengan memberikan informasi kepada pengguna apa yang terjadi pada setiap fitur yang dijalankan. Lalu, dapat diimplementasikan autentikasi login untuk pengguna sehingga setiap orang memiliki list productnya sendiri. Untuk bagian delete dan edit dapat ditambah autorisasi sehingga hanya pengguna yang memiliki akses yang dapat melakukan action tersebut.

### Reflection 1.2

- Setelah menulis unit test, saya merasa lebih percaya diri untuk melakukan perubahan atau refactoring karena saya memiliki jaring pengaman yang akan memberitahu jika ada fitur yang rusak. Namun, saya menyadari bahwa 100% code coverage tidak menjamin kode bebas bug. Coverage hanya menghitung persentase baris kode yang dieksekusi saat tes, namun tidak memastikan bahwa logika bisnis benar dalam segala skenario. Logical Error atau edge cases yang tidak terpikirkan saat menulis tes masih bisa terjadi meskipun baris kodenya sudah ter-cover. Oleh karena itu, code review dan pengujian skenario negatif tetap krusial.

- Jika saya membuat class functional test baru dengan menyalin prosedur setup dan variabel instance yang sama persis dari CreateProductFunctionalTest.java, itu akan menurunkan kualitas kode karena melanggar prinsip Don't Repeat Yourself. Terjadi duplikasi kode pada bagian konfigurasi port, base URL, dan setup server. 

- Isu ini akan menyulitkan pemeliharaan, karena jika suatu saat mekanisme setup berubah, saya harus mengubahnya di semua file test satu per satu.