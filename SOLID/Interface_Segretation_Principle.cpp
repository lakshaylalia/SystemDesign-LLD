#include <bits/stdc++.h>
using namespace std;

/* class Uber {
    public:
    virtual void bookRide() = 0;
    virtual void payRide() = 0;
    virtual void acceptRide() = 0;
    virtual void drive() = 0;
    virtual void endRide() = 0;
}; */

/* class Rider: public Uber {
    public:
    void bookRide() override {} // needed
    void payRide() override {} // needed
    void acceptRide() override {} // not needed
    void drive() override {} // not needed
    void endRide() override {} // not needed
}; */

/* class Driver: public Uber {
    public:
    void acceptRide() override {} // needed
    void drive() override {} // needed
    void endRide() override {} // needed
    virtual void bookRide() override {} // not needed
    virtual void payRide() override {} // not needed
}; */

class RiderInterface {
    public:
    virtual void bookRide() = 0;
    virtual void payRide() = 0;
}; 

class DriverInterface {
    public:
    virtual void acceptRide() = 0;
    virtual void drive() = 0;
    virtual void endRide() = 0;
};

class Rider : public RiderInterface {
    public:
    void bookRide() override {}

    void payRide() override {}
};

class Driver : public DriverInterface {
    public:
    void acceptRide() override {}

    void drive() override {}

    void endRide() override {}
};

int main() {
    
    return 0;
}