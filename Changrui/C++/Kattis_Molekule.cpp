//
//  main.cpp
//  Kattis_Molekule
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
int vertexColor[100000+10],firstEle[100000+10],visited[100000+10];
vector<list<int>> adjacentLis;
void bfs(int startPos)
{
    queue<int> sequence;
    vertexColor[startPos]=0;
    sequence.push(startPos);
    while(!sequence.empty())
    {
        int u=sequence.front();
        sequence.pop();
        visited[u]=1;
        int paintColor=vertexColor[u]? 0:1;
        for (auto c: adjacentLis[u])
        {
            if (visited[c]==0)
            {
                sequence.push(c);
                vertexColor[c]=paintColor;
            }
        }
            
    }
}
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);cout.tie(NULL);
    int N; //ð  (2â¤ðâ¤100000)
    cin>>N;
    int a, b;//1â¤a,bâ¤100000
    adjacentLis.resize(N+1);
    for (int i=0; i<100000+10; i++)
    {
        vertexColor[i]=-1;
        firstEle[i]=-1;
        visited[i]=0;
    }
    for(int i=0; i<N-1; i++)
    {
        cin>>a>>b;
        adjacentLis[a].push_back(b);
        adjacentLis[b].push_back(a);
        firstEle[i]=a;
    }
    bfs(firstEle[0]);
    for (int i=0; i<N-1; i++)
    {
        cout<<vertexColor[firstEle[i]]<<endl;
    }
    return 0;
}

/*

 æ»ç»ï¼
 1. åé¢åè¦æ³æ¸æ¥ï¼åç¬è®°ï¼ æ åºæ¯ä¸ªåéçåå¼èå´ï¼ ä¸è¦ç¨ojè¯é
 2. ç¥è¯ç¹ï¼
    a. æ æ¯ä¸ä¸ªbipartite
    b. bipartiteå¯ä»¥ç¨æè²æ³æä¸¤ç±»ååºæ¥
    c. è¦æ³èµ°çæè¿ï¼å°±è¦è®©æ¯ä¸ªnodeç¸é»çæænodesåæ¶æåèªå·±ææåºå»ï¼ä¸¤ç±»nodesï¼
    d. å½graphæ¶åä¸¤ç±»nodesæ¶èèbipartite
            
 */
