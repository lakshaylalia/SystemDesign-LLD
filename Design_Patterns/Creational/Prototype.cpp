#include <bits/stdc++.h>
using namespace std;

class EmailTemplate {
    virtual void setContent(string content) = 0;
    virtual void send(string to) = 0;
};

/* class WelcomEmail : public EmailTemplate {
    private:
    string subject, content;

    public:
    WelcomEmail() {
        this->subject = "Welcome to our website";
        this->content = "Hi there! Thanks for joining our website";
    }

    void setContent(string content) override{
        this->content = content;
    }

    void send(string to) override {
        cout <<"Sending to " << to <<": [" << subject << "] " << content << endl;
    }
}; */

int main() {
    return 0;
}