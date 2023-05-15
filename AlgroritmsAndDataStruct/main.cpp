#include <iostream>
#include <vector>
#include <algorithm>

struct Edge {
    int from;
    int to;
    int weight;
};

bool compareWeights(const Edge& edge1, const Edge& edge2)
{
    return edge1.weight < edge2.weight;
}

int findParent(std::vector<int>& parentList, int node)
{
    if (parentList[node] == node)
    {
        return node;
    }

    return parentList[node] = findParent(parentList, parentList[node]);
}

void mergeSets(std::vector<int>& parentList, std::vector<int>& rankList, int node1, int node2)
{
    node1 = findParent(parentList, node1);
    node2 = findParent(parentList, node2);

    if (node1 == node2)
    {
        return;
    }
    if (rankList[node1] < rankList[node2])
    {
        parentList[node1] = node2;
    }
    else
    {
        parentList[node2] = node1;
        if (rankList[node1] == rankList[node2])
        {
            ++rankList[node1];
        }
    }
}

void processEdges(const std::vector<Edge>& edges, std::vector<int>& parentList, std::vector<int>& rankList, std::vector<int>& edgeValues, int& result)
{
    for (const Edge& edge : edges) {
        int from = edge.from;
        int to = edge.to;
        int weight = edge.weight;

        if (findParent(parentList, from) == findParent(parentList, to)) {
            continue;
        }

        edgeValues[from] = std::max(edgeValues[from], weight);
        edgeValues[to] = std::max(edgeValues[to], weight);

        mergeSets(parentList, rankList, from, to);

        result += weight;
    }
}


int main() {
    int vertex, NumEdges;
    int result = 0;
    int minDifference = INT_MAX;
    std::cin >> vertex >> NumEdges;

    std::vector<Edge> edges(NumEdges);
    std::vector<std::vector<int>> graph(vertex + 1, std::vector<int>(vertex + 1));
    std::vector<int> parentList(vertex + 1);
    std::vector<int> rankList(vertex + 1);
    std::vector<int> edgeValues(vertex + 1);

    for (int i = 0; i <= vertex; ++i) {
        parentList[i] = i;
        rankList[i] = 1;
    }

    for (int i = 0; i < NumEdges; ++i) {
        int from, to, weight;
        std::cin >> from >> to >> weight;

        edges[i].from = from;
        edges[i].to = to;
        edges[i].weight = weight;

        graph[from][to] = weight;
        graph[to][from] = weight;
    }

    std::sort(edges.begin(), edges.end(), compareWeights);
    processEdges(edges, parentList, rankList, edgeValues, result);


    for (int i = 0; i <= vertex; ++i) {
        int value = edgeValues[i];
        int difference = INT_MAX;

        for (int j = 0; j < NumEdges; ++j) {
            int from = edges[j].from;
            int to = edges[j].to;
            int weight = edges[j].weight;

            if ((from != i && to != i) || weight <= value) {
                continue;
            }
            difference = std::min(difference, weight - value);
        }

        minDifference = std::min(minDifference, difference);
    }
    std::cout << result << " " << result + minDifference;

    return 0;
}