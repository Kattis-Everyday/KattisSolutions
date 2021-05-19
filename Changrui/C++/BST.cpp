#include<iostream>
#include<string>
// Binary Search Tree operations in C++
using namespace std;

struct node {
    long long key;
  struct node *left, *right;
};

// Create a node
struct node *newNode(long long item) {
  struct node *temp = (struct node *)malloc(sizeof(struct node));
  temp->key = item;
  temp->left = temp->right = NULL;
  return temp;
}

// Inorder Traversal
void inorder(struct node *root) {
  if (root != NULL) {
    // Traverse left
    inorder(root->left);

    // Traverse root
    cout << root->key << " -> ";

    // Traverse right
    inorder(root->right);
  }
}

// Insert a node
struct node *insert(struct node *node, long long key) {
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
struct node *minValueNode(struct node *node) {
  struct node *current = node;

  // Find the leftmost leaf
  while (current && current->left != NULL)
    current = current->left;

  return current;
}

bool find(struct node *node, long long key)
{
    if (node==NULL) return 0;
    else if (node->key==key){
        return 1;
    }
    else if (node->key < key)
    {
        return find(node->right, key);
    }
    else{
        return find(node->left, key);
    }
}


struct node * leftMost(struct node * root)
{
    if (!root)
        return NULL;
    while (root->left)
        root = root->left;
    return root;
}
struct node *deleteNode(struct node *root) {
    if (!root->left) {
        struct node *child = root->right;
            root = NULL;
            return child;
        }
        else if (!root->right) {
        struct node * child = root->left;
            root = NULL;
            return child;
        }
     
        // node with two children: get inorder successor
        // in the right subtree
        struct node * next = leftMost(root->right);
     
        // copy the inorder successor's content to this node
        root->key = next->key;
     
        // delete the inorder successor
        root->right = deleteNode(root->right);
     
        return root;
}

struct node *removeRange(struct node *node, long long low, long long high)
{
    if (node==NULL) return NULL;
    node->left = removeRange(node->left, low, high);
    node->right = removeRange(node->right, low, high);
    if (node->key > low && node->key < high) return deleteNode(node);
    return node;
}



// Deleting a node
struct node *deleteNode(struct node *root, long long key) {
  // Return if the tree is empty
  if (root == NULL) return root;

  // Find the node to be deleted
  if (key < root->key)
    root->left = deleteNode(root->left, key);
  else if (key > root->key)
    root->right = deleteNode(root->right, key);
  else {
    // If the node is with only one child or no child
    if (root->left == NULL) {
      struct node *temp = root->right;
      free(root);
      return temp;
    } else if (root->right == NULL) {
      struct node *temp = root->left;
      free(root);
      return temp;
    }

    // If the node has two children
    struct node *temp = minValueNode(root->right);

    // Place the inorder successor in position of the node to be deleted
    root->key = temp->key;

    // Delete the inorder successor
    root->right = deleteNode(root->right, temp->key);
  }
  return root;
}

bool find_ith(struct node *root,long long n,int order) {
    static int count=0;
    bool flag=0;
    if (order==1)
    { count=0;
        return 1;
    }
    //cout<<"count:"<<count<<endl;
    if (root==NULL) return 0;
    if (count<=n){
        flag=find_ith(root->left,n,0)|flag;
        count++;
        if (count == n)
        {cout<<root->key;
         flag=1;
        }
        flag=find_ith(root->right,n,0)|flag;
        
    }
    return flag;
}


// Driver code
int main() {
  struct node *root = NULL;
    int n;
    cin>>n;
    for (int i=0; i<n; i++){
        string order;
        long long num;
        cin>>order>>num;
        if (order=="insert"){
            root = insert(root, num);
        }
        else if (order=="delete"){
            //struct node *target=find(root,num);
            root = deleteNode(root, num);
        }
        else if (order=="find_ith"){
            bool flag=find_ith(root,num,0);
            if (flag==1){
                cout<<endl;
            }
            else{
                cout<<"N\n";
            }
            find_ith(root,num,1);//default static
        }
        else if (order=="find")
        {
            bool flag=find(root, num);
            if (flag) cout<<"Y\n";
            else cout<<"N\n";
            
        }
        else if (order=="delete_interval")
        {
            long long b;
            cin>>b;
            long long a=num;
            root=removeRange(root, a, b);
        }
        else
        {
            inorder(root);
        }
            
    }
    
}
