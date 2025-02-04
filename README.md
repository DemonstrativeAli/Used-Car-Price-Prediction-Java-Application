Used Car Price Prediction App

Model Güncelleme

Bu proje, ikinci el araç fiyat tahmini yapmak için RandomForest modeli kullanmaktadır.
Model, RandomForest 1.5.2 sürümü ile dışarı aktarılmıştır, ancak şu an 1.6 sürümü kullanılmaktadır.
Eğer uyumsuzluk yaşarsanız, Jupyter Notebook veya ilgili Python ortamında hücreleri çalıştırarak modeli pickle formatında yeniden dışarı aktarabilirsiniz.

Flask API Bağlantısı

Flask API’yi başlattıktan sonra, API'nin oluşturduğu "http://10.xx" ile başlayan adresi aşağıdaki dosyalarda değiştirmelisiniz:

PredictionActivity sınıfının 92. satırında

ApiClient sınıfının 12. satırında

Bu değişiklikleri yaptıktan sonra uygulamanız, kendi API’niz ile iletişim kuracaktır.

Uygulama Arayüzü

Uygulama, kullanıcı dostu bir arayüz ile araç fiyat tahmini yapmanıza olanak tanır. Tahmin bölümü şu bilgileri alır:

Marka: Aracınızın markasını seçin.

Model: Aracınızın modelini girin.

Kilometre: Aracınızın mevcut kilometresini girin (mil cinsinden).

Motor Hacmi: Örneğin, 4.0 V8 motor için 4.0 olarak girin.

Yakıt Türü: Benzin, dizel, elektrik gibi yakıt türünü seçin.

Vites Türü: Manuel veya otomatik gibi vites seçeneklerinden birini seçin.

Tüm bilgileri girdikten sonra "Tahmin Yap" butonuna basarak aracınızın tahmini fiyatını görebilirsiniz.

