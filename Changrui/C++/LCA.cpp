#pragma GCC optimize(3,"Ofast","inline")
//#include <bits/stdc++.h>
#include<iostream>
#include<string>
// Binary Search Tree operations in C++

#include <iostream>
using namespace std;

struct node {
  int key;
  struct node *left, *right;
};

// Create a node
struct node *newNode(int item) {
  struct node *temp = (struct node *)malloc(sizeof(struct node));
  temp->key = item;
  temp->left = temp->right = NULL;
  return temp;
}

// Insert a node
struct node *insert(struct node *node, int key) {
  // Return a new node if the tree is empty
  if (node == NULL) return newNode(key);

  // Traverse to the right place and insert the node
  if (key < node->key)
    node->left = insert(node->left, key);
  else
    node->right = insert(node->right, key);

  return node;
}

// Find the inorder successor



struct node *lca(struct node *root, int n1, int n2) {
    if (root==NULL) return NULL;
    if (root->key>n1 && root->key>n2) return lca(root->left,n1,n2);
    if (root->key < n1 && root->key < n2) return lca(root->right, n1, n2);
    return root;
}



// Driver code
int main() {
  struct node *root = NULL;
    int n;
    cin>>n;
    for (int i=0; i<n; i++){
        int num;
        cin>>num;
        root=insert(root,num);
    }
    int a,b;
    cin>>a>>b;
    struct node * lcaIBST=lca(root,a,b);
    cout<<lcaIBST->key<<endl;
    
    
    
}
