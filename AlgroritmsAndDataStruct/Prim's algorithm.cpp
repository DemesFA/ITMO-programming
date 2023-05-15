#include <iostream>
#include <vector>
#include <queue>


struct Edge {
    int to, weight;
};

std::vector<std::vector<Edge>> graph;
std::vector<int> dist;

void Prim(int start) {
    int n = graph.size();
    std::priority_queue<std::pair<int, int>, std::vector<std::pair<int, int>>, std::greater<>> pq;
    pq.push(std::make_pair(0, start));
    dist[start] = 0;
    while (!pq.empty()) {
        int CurrVertex = pq.top().second;
        pq.pop();
        for (auto e : graph[CurrVertex]) {
            if (dist[e.to] > e.weight) {
                dist[e.to] = e.weight;
                pq.push(std::make_pair(dist[e.to], e.to));
            }
        }
    }
}

int main() {
    int vertex, edges;
    std::cin >> vertex >> edges;
    graph.resize(vertex);
    dist.assign(vertex, INT32_MAX);
    for (int i = 0; i < edges; ++i) {
        int u, v, w;
        std::cin >> u >> v >> w;
        graph[u - 1].push_back({ v - 1, w });
        graph[v - 1].push_back({ u - 1, w });
    }
    Prim(0);
    int total_weight = 0;
    for (int i = 0; i < vertex; ++i) {
        total_weight += dist[i];
    }
    std::cout << total_weight << std::endl;
    return 0;
}
