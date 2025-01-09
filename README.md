**Link Earn In Sketchware Pro**:  

---

# 🌟 **Link Earn**  

🎉 **Version:** Faster Panel v1.0  
🚀 **UI Framework:** Material 3  
📱 **Supports Android 15**  

---

![Logo](https://raw.githubusercontent.com/FasterSoftwareDeveloper/Link-Earn-Sketchware-Pro-Project/refs/heads/master/thumbnail.png)

## Links
YouTube Video: https://youtu.be/pJy5yi88-mE?si=x83Gr2nsOpP5kkAb

Telegram Post: https://t.me/fastersoftwaredeveloper/327

### 📖 **About the App**  

**Link Earn** is a modern, intuitive Android app designed with simplicity and speed in mind. With its robust features and seamless **Admin Panel**, it caters to users and developers alike, providing a cutting-edge solution for task management and monetization.  

Whether you're publishing on the **Play Store** or using it for personal projects, **Link Earn** ensures a lag-free, easy-to-configure experience.  

### 🚀 **What Makes Link Earn Special?**  
1. **Blazing-Fast Admin Panel**:  
   - Built with performance in mind, the **Faster Panel V1.0** ensures you can manage tasks and user data quickly and efficiently.  
2. **Material 3 UI**:  
   - Enjoy a polished, modern design that integrates the latest Android UI standards.  
3. **Compatibility**:  
   - Fully supports Android 15 while maintaining compatibility with Android 9+.  
4. **Precision in Task Costing**:  
   - Automatically and accurately calculates the cost of adding tasks to ensure seamless operations.  

---

### 💼 **Features**  

#### **User Panel**  
The app provides users with a comprehensive interface to manage their activities:  
- 🌟 **Main Activity (Splash Screen)**: Welcomes users with a sleek animation.  
- 🔧 **App Maintenance**: Keeps users informed during service interruptions.  
- 🔄 **App Update**: Notifies users of available updates.  
- 🚫 **Account Blocked**: Displays a message if the account is restricted.  
- 🔑 **Authentication**: Secure login and sign-up features.  
- 🏠 **Dashboard**: Central hub for all app activities.  
- ✅ **Task Creation**: Manage tasks effortlessly.  
- 👋 **Welcome Screen**: Personalized greetings for users.  
- 🖥 **Task View (WebView)**: Efficient task viewing interface.  
- 📡 **No Internet Error Screen**: Graceful handling of offline scenarios.  
- 🔍 **Search Functionality**: Easy navigation and quick access to tasks.  
- 🎉 **Success Messages**: Confirmation for completed actions.  
- 💰 **Wallet Management**: Track earnings effortlessly.  
- 🏅 **Leaderboard**: Compete with others and track your ranking.  

#### **Admin Panel**  
Manage the app, users, and tasks efficiently with the **Faster Panel V1.0**.  

---

### 🔧 **Project Requirements**  

#### **Minimum Requirements**:  
- **Sketchware Pro** (Nightly Build, Min API 26).  
- **Minimum API Level**: 23.  
- **Target API Level**: 35.  
- **Supported Android Versions**: Android 9+ (up to Android 15).  

#### **Why Choose Sketchware Pro?**  
Sketchware Pro enables rapid development and customization, ensuring that even non-developers can set up the app with ease.  

---

### ⚙️ **Setup Instructions**  

---

## **File Map**

### **User Panel** (`Link_Earn_User`)
1. Navigate to the folder structure:  
   ```
   data  
   local_libs  
   resources  
   project  
   ```
2. Select all files in the folder and compress them into a `.zip` file.  
3. Rename the `.zip` file to `linkEarn.swb`.  

---

### **Admin Panel** (`Link_Earn_Admin`)  
1. Navigate to the folder structure:  
   ```
   data  
   local_libs  
   resources  
   project  
   ```
2. Select all files in the folder and compress them into a `.zip` file.  
3. Rename the `.zip` file to `linkEarnAdmin.swb`.  

---

## **Block & Menu Import Instructions**

1. Go to the **Import** folder and locate the following files:
   - `block.json`  
   - `palette.json`  
   - `menu.json`  

2. Navigate to the **Sketchware resources directory**:  
   ```
   /storage/emulated/0/.sketchware/resources/block/My Block/
   ```

3. Paste the files (`block.json`, `palette.json`, `menu.json`) into this directory.  
   - **Warning**: Pasting these files will overwrite any existing custom blocks. Ensure you back up your current blocks before proceeding.  

4. Restart Sketchware Pro to apply changes.

---

1. **Firebase Configuration**:  
   - Add your **Firebase details** during the first-time setup.  
   - Replace the default `service-account.json` file in the `Assets Folder` with your own.  
     - 📍 Navigate to Firebase project settings → **Service Accounts** tab → **Generate new private key**.  
     - Save and replace the file accordingly.
     - Now go to firebase select your project then click three dot menu › project settings › General › Select Your App › download google-services.json
     - Go to Sketchware Pro open your project then click there dot menu › Library › Firebase › Click Import from google-services.json" and import the json file.

2. **Edit `strings.xml`**:  
   - Customize the app name and other relevant details:  
     ```xml
     <string name="app_name">Link Earn</string>
     ```

3. **Set Firebase Rules**:  
   Add the following Firebase rules to ensure secure read and write operations:  
   ```json
   {
     "rules": {
       "others": {
         ".read": "auth != null || auth == null",
         ".write": "auth != null"
       },
       "$otherNodes": {
         ".read": "auth != null",
         ".write": "auth != null"
       }
     }
   }
   ```

4. **Add Firebase Authentication User**:  
   Ensure the app functions correctly by adding a default user in Firebase Authentication:  
   ```  
   Email: anonymously@gmail.com  
   Password: anonymously1234  
   ```  

5. **First-Time Setup**:  
   Simply download the project, replace the necessary files, and you're ready to go.  

---

### 🌟 **Key Features and Benefits**  

- ⚡ **Lightning-Fast Performance**: Optimized for minimal lag and maximum responsiveness.  
- 🔒 **Enhanced Security**: Firebase Authentication ensures your data remains secure.  
- 📱 **Wide Compatibility**: Supports multiple Android versions, ensuring versatility.  
- 🎨 **Modern Design**: Material 3 UI offers a sleek and intuitive user experience.  
- 🛠 **Customizable**: Easily adapt the app to your needs with simple configurations.  

---

### 🤝 **Contributions and Collaboration**  

We welcome contributions! If you encounter bugs, have ideas for new features, or want to improve the app, feel free to:  
1. Fork the repository.  
2. Make changes.  
3. Submit a **Pull Request** for review.  

Your contributions will help make **Link Earn** better for everyone!  

---

### ♥️ **Support and Donations**  

If you appreciate the hard work behind this project, consider supporting the creator:  
- **Binance Pay ID**: 766720837  

Every donation helps in maintaining and improving this app.  

---

### 📢 **Connect with the Creator**  

Follow **Faster Software Developer** for updates, tutorials, and more:  
- **YouTube Channel**: [Faster Software Developer](#)  
- **Telegram**: [@TakbirHassan](#)  

Stay updated with the latest tips and tricks to maximize your app's potential!  

---

### ⚠️ **Important Warnings**  

- Do not share the project files (SWB files or ZIP files) on YouTube Channel.  
- Respect the creator's efforts and time by adhering to these guidelines. You can publish this app in play store and another app store.

---

### 🎉 **Final Note**  

We hope you enjoy using **Link Earn**! With its powerful features and sleek design, it’s the perfect solution for your task management and monetization needs.  

💻 **Happy Coding!**
