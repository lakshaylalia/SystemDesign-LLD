#include <bits/stdc++.h>
using namespace std;

/* class TaxCalculator
{
public:
    // Orignal Code
    // double calculateTax(double amount) {
    //     return (amount + amount * 0.18);
    // }

    // Code after adding region, now we have to make sure it must be added all other places hwere it is used
    // double calculateTax(double amount, string region) {
    //     if(region == "IN") {
    //         return (amount + amount * 0.18);
    //     }
    //     else if(region == "US") {
    //         return (amount + amount * 0.1);
    //     }
    //     return amount;
    // }
};
*/

/* class InvoiceService
{
public:
    void calculate()
    {
        TaxCalculator taxCalculator;
        // After adding region it will cause error as region is not defined in the function
        taxCalculator.calculateTax(100);
    }ṇ
}; */

class TaxCalculator
{
public:
    virtual double calculateTax(double amount) = 0;
};

class IndianTaxCalculator : public TaxCalculator
{
public:
    double calculateTax(double amount) override
    {
        return amount + amount * 0.18;
    }
};

class USATaxCalculator : public TaxCalculator
{
public:
    double calculateTax(double amount) override
    {
        return amount * 0.1;
    }
};

class UKTaxCalculator : public TaxCalculator
{
public:
    double calculateTax(double amount) override
    {
        return amount * 0.12;
    }
};

class Invoice
{
private:
    double amount;
    TaxCalculator *taxCalculator;

public:
    Invoice(double amount, TaxCalculator *taxCalculator) : amount(amount), taxCalculator(taxCalculator) {}

    double getAmount()
    {
        return amount + taxCalculator->calculateTax(amount);
    }
};

int main()
{
    Invoice IndianTax(100, new IndianTaxCalculator());
    cout << IndianTax.getAmount() << endl;

    Invoice USATax(100, new USATaxCalculator());
    cout << USATax.getAmount() << endl;

    Invoice UKTax(100, new UKTaxCalculator());
    cout << UKTax.getAmount() << endl;
    return 0;
}