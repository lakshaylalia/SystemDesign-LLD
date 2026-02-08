#include <bits/stdc++.h>
using namespace std;

class Logistics{
    public:
    virtual void send() = 0;
};

class Road : public Logistics{
public:
    void send() override{
        cout << "Send by road";
    }
};

class Air : public Logistics{
public:
    void send() override{
        cout << "Send by air";
    }
};

class LogisticFactory { // Factory that returns Logistics object according to mode
public:
    static Logistics* getLogistic(string mode) {
        if(mode == "road") {
            return new Road();
        }
        return new Air();
    }
};

class LogisticService {
public:
    void send(string mode) {
      Logistics* logistic = LogisticFactory::getLogistic(mode);
      logistic->send();
    }
};

int main() {
    
    return 0;
}