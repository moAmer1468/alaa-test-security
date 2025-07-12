
---

## 📄 `README.md` – مشروع Spring Boot مع JWT و JDBC

````markdown
# 🚗 Spring Boot JWT Security with JDBC - Car Management API

ده مشروع REST API بسيط معمول باستخدام Spring Boot، بيوفر إمكانية إدارة بيانات العربيات، ومؤمن باستخدام JWT + JDBC authentication (يعني المستخدمين محفوظين في قاعدة البيانات).

---

## 🔐 التكنولوجيات المستخدمة

- Java 21
- Spring Boot 3.2
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- MySQL
- JDBC UserDetailsManager
- Lombok
- Exception Handling (Global)

---

## ✅ المميزات

- تسجيل الدخول باستخدام JWT Token
- حماية Endpoints بصلاحيات (Roles): `USER`, `ADMIN`
- إنشاء وتحديث وحذف عربيات → Admin فقط
- قراءة العربيات → Admin & User
- معالجة الأخطاء بشكل احترافي باستخدام Exception Handling
- فلتر مخصص لقراءة التوكن والتحقق منه `JwtAuthFilter`

---

## 📦 Endpoints

| Endpoint | الطريقة | الوصول | الوصف |
|----------|---------|--------|-------|
| `/auth/login` | `POST` | عام | تسجيل الدخول والحصول على توكن |
| `/api/cars` | `GET` | USER أو ADMIN | قراءة كل العربيات |
| `/api/cars/{id}` | `GET` | USER أو ADMIN | قراءة عربية معينة |
| `/api/cars` | `POST` | ADMIN فقط | إضافة عربية جديدة |
| `/api/cars/{id}` | `PUT` | ADMIN فقط | تعديل بيانات عربية |
| `/api/cars/{id}` | `DELETE` | ADMIN فقط | حذف عربية |

---

## 🧪 بيانات تسجيل الدخول (افتراضيًا)

```json
{
  "username": "admin",
  "password": "admin"
}
````

```json
{
  "username": "user",
  "password": "password"
}
```

> بيتم إنشاؤهم أوتوماتيك في `Main.java` باستخدام `JdbcUserDetailsManager`.

---

## 🛡️ صلاحيات الوصول

| الدور | ما يستطيع فعله            |
| ----- | ------------------------- |
| USER  | قراءة بيانات العربيات فقط |
| ADMIN | إضافة وتعديل وحذف وقراءة  |

---

## ⚙️ إعداد JWT

تم إنشاء الكلاسات التالية لتفعيل JWT:

* `JwtUtil` → مسؤول عن توليد وفك التوكن
* `JwtAuthFilter` → فلتر بيقرأ التوكن من كل request
* `SecurityConfig` → إعداد الصلاحيات وتركيب الفلتر
* `AuthController` → endpoint لتسجيل الدخول وإرجاع التوكن

---

## ❌ التعامل مع الأخطاء (Exception Handling)

تم تفعيل معالجة الأخطاء بشكل عام عن طريق:

* كلاس `GlobalExceptionHandler`
* بيستخدم `@ControllerAdvice` و `@ExceptionHandler`
* بيرجع رسائل واضحة عند حدوث:

    * NotFoundException
    * BadRequestException
    * Unauthorized access
    * وغيرها

---

## 🛠️ خطوات التشغيل

1. تأكد إن MySQL شغال وأنك عامل قاعدة بيانات اسمها `jwt_db`
2. شغل التطبيق من `Main.java`
3. استخدم Postman لتجربة الدخول على `/auth/login`
4. انسخ التوكن واستخدمه في الهيدر:

   ```
   Authorization: Bearer <your-token>
   ```


## 📌 ملاحظات

* تأكد من وجود الجداول: `users` و `authorities` في قاعدة البيانات
* تم استخدام `BCryptPasswordEncoder` لتشفير كلمات السر

---

## 📚 مؤلف المشروع

محمد علاء – مشروع تعليمي للتدريب على Spring Security + JWT + JDBC

