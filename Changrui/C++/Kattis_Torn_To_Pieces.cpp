//
//  main.cpp
//  Kattis_Torn To Pieces
//
//  Created by mcr on 27/10/2020.
//


#pragma GCC optimize(3,"Ofast","inline")
//#include <bits/stdc++.h>
#include<iostream>
#include<sstream>
#include<iomanip>
#include<cmath>
# include <string.h>
#include<math.h>
#include<string>
#include<vector>
#include<queue>
#include<map>
#include<stack>
#include<set>
#include<list>
#include<unordered_set>
#include<unordered_map>


#include<utility>
#include<tuple>

#include<algorithm>


using namespace std;

typedef long long ll;
typedef long double ld;
typedef unsigned int uint;
typedef unsigned long long ull;

/*
const ld  pi   = 4.0*atanl(1.0);
const int iinf = 1e9 + 10;
const ll  inf  = 1e18 + 10;
const int mod  = 1000000007;
const ld  prec = .000001;
*/

unordered_map<string,list<string>> adjacentLis;
unordered_map<string, int> visited;
deque<string> path;
string startPos, destination;
stack<string> dfsS;
bool flag=false;
stack<int> n;
void dfs (string currPos)
{
    
    visited[currPos]=1;
    for (auto c: adjacentLis[currPos])
    {
        if (c==destination)
        {
            path.push_back(c);
            flag=1;
            return;
        }
        if(visited[c]) continue;
        path.push_back(c);
        dfs(c);
        if (flag) return;
        path.pop_back();
    }
}

void NonReccur_dfs()
{
    dfsS.push(startPos);
    path.push_back(startPos);
    while(!dfsS.empty())
    {
        string p=dfsS.top();
        dfsS.pop();
        path.push_back(p);
        visited[p]=1;
        int prepe=0;
        for (auto c: adjacentLis[p])
        {
            
            if (c==destination)
            {
                path.push_back(c);
                while(!path.empty())
                {
                    cout<<path.front()<<" ";
                    path.pop_front();
                    
                }
                flag=true;
                cout<<endl;
                return;
                
            }
            else if (visited[c]==0)
            {
                
                dfsS.push(c);
                prepe++;
            }
            
        }
        int te=n.top();
        n.pop();
        te--;
        if (te<=0) path.pop_back();
        else n.push(te);
        n.push(prepe);
        
        
    }
}
 
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);cout.tie(NULL);
    int N;//2??????????32
    cin>>N;
    cin.ignore();
    string temp, a, b;
 
    for (int i=0 ;i< N;i++)
    {
        getline(cin,temp);
        istringstream ss(temp);
        ss>>a;
        visited[a]=0;
        while(ss>>b)
        {
            visited[b]=0;
            adjacentLis[a].push_back(b);
            adjacentLis[b].push_back(a);
        }
    }
    
    cin>>startPos>>destination;
    if (!visited.count(startPos)||!visited.count(destination))
    {
        cout<<"no route found"<<endl;
        return 0;
    }
    path.push_back(startPos);
    
    dfs(startPos);
    if (flag==false)
    {
        cout<<"no route found"<<endl;
    }
    else
    {
        while(!path.empty())
        {
            cout<<path.front()<<" ";
            path.pop_front();
            
        }
        cout<<endl;
    }
    return 0;
}


/*
 ?????????
 1. dfs????????????????????????recursion???
 2. ???recursion????????????????????????????????? ???????????????stack?????????????????????
 3. ??????????????? dfs?????????
    ???????????????
    ?????????????????????????????????(???????????????????????????)??????C++?????????stack???????????????

    ??????
    ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????DFS??????????????????????????????

        ???????????????
        1???????????????????????????????????????
        2??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        3???????????????2???
        4???????????????????????????????????????????????????????????????????????????????????????????????????2???
        5???????????????4???
        6?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
 
 4. ????????? ???????????????????????????????????????
        a. ?????????map?????????ordered, fifo) ?????????????????????????????????a??????b?????????, ????????????????????????????????????
        b. ???????????????????????????map???unordered?????? ????????????node?????????????????????node??? ?????????node???????????? ??????ta?????????????????? ???????????????. erase
 
 */
