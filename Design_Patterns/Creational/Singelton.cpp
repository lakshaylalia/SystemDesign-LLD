#include <bits/stdc++.h>
using namespace std;

class JudgeAnalysis {
    private:
    int run = 0;
    int submit = 0;

    public:
    void countRun() {
        run++;
    }

    void countSubmit() {
        submit++;
    }

    int getRunCount() {
        return run;
    }

    int getSubmitCount() {
        return submit;
    }
}; 

// Eager Loading
class JudgeAnalysisEager {
    private:
    // This is getting loaded when the program starts, 
    // thats why it is called Eager Loading
    static JudgeAnalysisEager instance; 

    JudgeAnalysisEager() {}

    public:
    static JudgeAnalysisEager getInstance() {
        return instance;
    }
}; 
JudgeAnalysisEager JudgeAnalysisEager::instance;

// Lazy Loading
class JudgeAnalysisLazy {
    private:
    // This will only created after the class is loaded 
    // thats why its known as Lazy Loading
    static JudgeAnalysisLazy *instance;

    JudgeAnalysisLazy() {}

    public:
    static JudgeAnalysisLazy& getInstance() {
        if(instance == nullptr) {
            instance = new JudgeAnalysisLazy();
        }
        return *instance;
    
    
    }
};
JudgeAnalysisLazy *JudgeAnalysisLazy::instance = nullptr;

class LazySync {
    private:
    static LazySync *instance;
    static mutex mtx;

    LazySync() {}

    public:
    static LazySync& getInstance() {
        lock_guard<mutex> lock(mtx); // lock the mutex
        if(instance == nullptr) {
            instance = new LazySync();
        }
        return *instance;
    }
};
LazySync *LazySync::instance = nullptr;
mutex LazySync::mtx;


class LazySyncDouble {
    private:
    static LazySyncDouble *instance;
    static mutex mtx;

    LazySyncDouble() {}

    public:
    static LazySyncDouble& getInstance() {
        if(instance == nullptr) { // first check if the instance is null
            lock_guard<mutex> lock(mtx); // if null then lock the mutex
            if(instance == nullptr) { // again checks if the instance is null
                instance = new LazySyncDouble();
            }
        }
        return *instance;
    }
};
LazySyncDouble *LazySyncDouble::instance = nullptr;
mutex LazySyncDouble::mtx;

class MeyerSingleton {
    private:
    MeyerSingleton() {}

    public:
    static MeyerSingleton& getInstance() {
        static MeyerSingleton instance;
        return instance;
    }
};

int main() {
    return 0;
}