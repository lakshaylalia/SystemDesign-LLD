#include <bits/stdc++.h>
using namespace std;

/* class BurgerMeal {
    private:
    // mandatory parameters
    string bun, patty;

    // optional
    string sides;
    vector<string> toppings;
    // bun and patty are compulsary for making a burger meal
    //   But other can be null ifclient don't want them, so does client have to write null
    //   for all of them in default constructor 
    public:
    // We can made multiple constructors for making different types of burger meals, 
    // but in larger cdebase with large number of parameters  this become very unreadable 
    BurgerMeal(string bun, string patty): bun(bun), patty(patty) {}
    BurgerMeal(string bun, string patty, string sides): bun(bun), patty(patty), sides(sides) {}
    BurgerMeal(string bun, string patty, string sides, vector<string> toppings): bun(bun), patty(patty), sides(sides), toppings(toppings) {}
}; */

class BurgerMeal {
    public:
    class BurgerBuilder {
        private:
        // Required
        string bunType, patty;

        // Optional
        bool hasCheese = false;
        string side = "", drinks = "";
        vector<string> toppings;

        friend class BurgerMeal;

        public:
        BurgerBuilder(string bunType, string patty) : bunType(bunType), patty(patty) {}

        BurgerBuilder& withCheese(bool hasCheese) { // '&' is used to pass the object by reference
            this->hasCheese = hasCheese;
            return *this; // 'this' return the reference of calling object and * is used to derefernce the pointer
        }

        BurgerBuilder& withSide(string side) {
            this->side = side;
            return *this;
        }

        BurgerBuilder& withDrinks(string drinks) {
            this->drinks = drinks;
            return *this;
        }

        BurgerBuilder& withToppings(vector<string> toppings) {
            this->toppings = toppings;
            return *this;
        }

        BurgerMeal build() {
            return BurgerMeal(*this); // this method is used to create an object of BurgerMeal class and here '*this' is BurgerBuilder object
        }
    };


    private:
    // Required
    string bunType, patty;
    
    // Optional
    bool hasCheese;
    string side, drinks;
    vector<string> toppings;

    BurgerMeal(const BurgerBuilder& builder) {
        this->bunType = builder.bunType;
        this->patty = builder.patty;
        this->hasCheese = builder.hasCheese;
        this->side = builder.side;
        this->drinks = builder.drinks;
        this->toppings = builder.toppings;
    }
};


int main() {
    BurgerMeal plainBurger = BurgerMeal::BurgerBuilder("wheat", "chicken").build();
    BurgerMeal cheeseBurger = BurgerMeal::BurgerBuilder("wheat", "chicken").withCheese(true).build();
    return 0;
}