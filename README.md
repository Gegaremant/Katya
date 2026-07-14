# Katya AI Assistant

**[English](#english) | [Русский](#русский)**

---

<a name="english"></a>
## 🇬🇧 English

<img src="https://img.shields.io/badge/Platform-Android-34a853.svg?logo=android" alt="Android" />
<div align="center">
<br>
<img src="site/img/logo_animation.gif" height="80">
<br>
<br>

An **open-source AI assistant with persistent memory** designed specifically for Android devices.

*Note: The server backend infrastructure for Katya is deployed via the [SmartBotHelper](https://github.com/Gegaremant/SmartBotHelper) repository.*

</div>

### ✨ Key Features

- **Offline Wake Word**: Detects "Привет Катя" locally using Vosk speech recognition without an internet connection.
- **Direct Ollama Connection**: Tunnels traffic through SSH directly to your private server, completely bypassing cloud API limits.
- **Server Monitoring**: Real-time SSH monitoring overlay that displays CPU, RAM, and GPU usage of your connected Linux server.
- **Persistent Memory**: Automatically remembers important facts, details, and preferences across conversations.
- **Interactive UI**: The AI can generate fully interactive screens (dashboards, recipes, brainstorms) instead of just plain text.

### 📥 Downloads

| Platform | Format | Download |
|----------|--------|----------|
| Android | APK | [GitHub Releases](https://github.com/Gegaremant/Katya/releases) |

### 🧠 Architecture

```text
               ┌─────────────────────────┐
               │          Chat           │
               │                         │
               │  prompt + memories      │
               │        │                │
               │        ▼                │
               │    ┌────────┐           │
               │    │   AI   │◀─┐        │
               │    └───┬────┘  │        │
               │        │   tool calls   │
               │        │   & results    │
               │        ▼      │        │
               │    ┌────────┐ │        │
               │    │ Tools  │─┘        │
               │    └───┬────┘          │
               │        │               │
               └────────┼───────────────┘
                        │ store / recall
                        ▼
               ┌─────────────────┐    hitCount >= 5
               │     Memory      │───────────────────┐
               │                 │                   │
               │  facts, prefs,  │                   ▼
               │  learnings      │          ┌────────────────┐
               │                 │◀─delete──│ Promote into   │
               └─────────────────┘          │ System Prompt  │
                        ▲                   └────────────────┘
                        │ reviews
                        │
               ┌─────────────────┐
               │    Heartbeat    │
               │                 │
               │  autonomous     │
               │  self-check     │
               │  every 30 min   │
               │  (8am–10pm)     │
               │                 │
               │  all good?      │
               │  → stays silent │
               │  needs action?  │
               │  → notifies user│
               └─────────────────┘
```

---

<a name="русский"></a>
## 🇷🇺 Русский

<img src="https://img.shields.io/badge/Platform-Android-34a853.svg?logo=android" alt="Android" />
<div align="center">
<br>
<img src="site/img/logo_animation.gif" height="80">
<br>
<br>

**Голосовой ассистент с искусственным интеллектом и постоянной памятью**, разработанный специально для Android-устройств.

*Примечание: Инфраструктура сервера для Кати разворачивается через репозиторий [SmartBotHelper](https://github.com/Gegaremant/SmartBotHelper).*

</div>

### ✨ Ключевые возможности

- **Офлайн активация голосом**: Локальное распознавание фразы "Привет Катя" с помощью движка Vosk, без необходимости интернета.
- **Прямое подключение к Ollama**: Работа через встроенный SSH туннель напрямую к вашему приватному серверу (никаких лимитов облачных API и платных подписок).
- **Мониторинг сервера**: Оверлей в реальном времени с отображением загрузки CPU, RAM и GPU с вашего Linux-сервера по SSH.
- **Постоянная память**: Катя автоматически запоминает важные факты и ваши предпочтения из всех предыдущих диалогов.
- **Интерактивный UI (Карточки)**: ИИ может генерировать не только скучный текст, но и интерактивные экраны-виджеты.

### 📥 Скачать

| Платформа | Формат | Ссылка |
|----------|--------|----------|
| Android | APK | [GitHub Releases](https://github.com/Gegaremant/Katya/releases) |

### 🧠 Архитектура

```text
               ┌─────────────────────────┐
               │           Чат           │
               │                         │
               │  запрос + воспоминания  │
               │        │                │
               │        ▼                │
               │    ┌────────┐           │
               │    │   ИИ   │◀─┐        │
               │    └───┬────┘  │        │
               │        │вызовы функций  │
               │        │и результаты    │
               │        ▼      │        │
               │    ┌────────┐ │        │
               │    │Инструм.│─┘        │
               │    └───┬────┘          │
               │        │               │
               └────────┼───────────────┘
                        │ запись/чтение
                        ▼
               ┌─────────────────┐    hitCount >= 5
               │     Память      │───────────────────┐
               │                 │                   │
               │ факты, вкусы,   │                   ▼
               │ знания          │          ┌────────────────┐
               │                 │◀─удал.───│ Перенос в      │
               └─────────────────┘          │ Системный Промпт│
                        ▲                   └────────────────┘
                        │ проверки
                        │
               ┌─────────────────┐
               │   Сердцебиение  │
               │   (Heartbeat)   │
               │                 │
               │ авто-проверка   │
               │ каждые 30 мин   │
               │ (с 8:00 до 22:00)│
               │                 │
               │ всё хорошо?     │
               │ → молчит        │
               │ нужны действия? │
               │ → пишет юзеру   │
               └─────────────────┘
```
