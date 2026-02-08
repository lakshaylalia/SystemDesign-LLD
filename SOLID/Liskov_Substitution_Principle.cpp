#include <bits/stdc++.h>
using namespace std;

class Notificatiion {
    public:
    virtual void notify() {
        cout << "Notificatiion" << endl;
    }
};

class TextNotification : public Notificatiion {
    public:
    void notify() {
        cout << "Text Notification" << endl;
    }
};

class EmailNotification : public Notificatiion {
    public:
    void notify() {
        cout << "Email Notification" << endl;
    }
};

int main() {
    Notificatiion *notification = new EmailNotification();
    notification->notify();
    return 0;
}