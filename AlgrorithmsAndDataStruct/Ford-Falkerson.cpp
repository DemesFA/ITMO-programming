#include <iostream>
#include <vector>
#include <limits>

using namespace std;

const int INF = numeric_limits<int>::max();

struct Edge {
    int to;
    int capacity;
    int flow;
    int rev;

    Edge(int to, int capacity, int rev) : to(to), capacity(capacity), flow(0), rev(rev) {}
};

void addEdge(vector<vector<Edge>>& graph, int from, int to, int capacity) {
    graph[from].push_back(Edge(to, capacity, graph[to].size()));
    graph[to].push_back(Edge(from, 0, graph[from].size() - 1));
}

int dfs(vector<vector<Edge>>& graph, vector<bool>& visited, int u, int minCapacity) {
    if (u == graph.size() - 1) {
        return minCapacity;
    }

    visited[u] = true;

    for (auto& edge : graph[u]) {
        if (!visited[edge.to] && edge.capacity > edge.flow) {
            int updatedFlow = dfs(graph, visited, edge.to, min(minCapacity, edge.capacity - edge.flow));
            if (updatedFlow > 0) {
                edge.flow += updatedFlow;
                graph[edge.to][edge.rev].flow -= updatedFlow;
                return updatedFlow;
            }
        }
    }

    return 0;
}

int fordFulkerson(vector<vector<Edge>>& graph) {
    int maxFlow = 0;

    while (true) {
        vector<bool> visited(graph.size(), false);
        int updatedFlow = dfs(graph, visited, 0, INF);

        if (updatedFlow == 0) {
            break;
        }

        maxFlow += updatedFlow;
    }

    return maxFlow;
}

int main() {
    int num_of_vertex, num_of_edge;
    cin >> num_of_vertex >> num_of_edge;

    vector<vector<Edge>> graph(num_of_vertex);

    for (int i = 0; i < num_of_edge; ++i) {
        int u, v, capacity;
        cin >> u >> v >> capacity;
        addEdge(graph, u - 1, v - 1, capacity);
    }

    int maxFlow = fordFulkerson(graph);

    cout << maxFlow << endl;

    return 0;
}
