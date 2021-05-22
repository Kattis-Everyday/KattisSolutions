#include <iostream>
#include <queue>
#include <cmath>
using namespace std;

int N, ans;
bool isComposite[50000];  // enough for sqrt(N), where N <= 10^9

bool isPrime(int k) {
    for (int i = 2; i <= sqrt(k); i++) {
        if (k % i == 0) return false;
    }

    return true;
}

int main() {
    cin>>N;
    if (isPrime(N)) {
        cout<<1;
        return 0;
    }

    // Pre-processing, Sieve of Eratosthenes
    int M = sqrt(N);
    queue<int> primes;

    for (int i = 2; i <= M; i++) {
        if (isComposite[i]) continue;

        primes.push(i);
        for (int j = 2; j <= M/i; j++) {
            isComposite[i*j] = true;
        }
    }

    while (!primes.empty() && N >= primes.front()) {
        if (N % primes.front() == 0) {
            N /= primes.front();
            ans++;
        } else {
            primes.pop();
        }
    }

    if (N > 1) ans++;
    cout<<ans<<'\n';
    return 0;
}
