#include <bits/stdc++.h>

using namespace std;


bool comp(const tuple<int, int, int>& tup1, const tuple<int, int, int>& tup2)
{
    return get<2>(tup1) < get<2>(tup2);
}

int findParent(vector<int>& visList, int first)
{
    if (visList[first] == first)
    {
        return first;
    }

    return visList[first] = findParent(visList, visList[first]);
}

void merge(vector<int>& visList, vector<int>& priorList, int first, int second)
{
    first = findParent(visList, first);
    second = findParent(visList, second);

    if (first == second)
    {
        return;
    }

    if (priorList[first] < priorList[second])
    {
        swap(first, second);
    }

    visList[second] = first;

    if (priorList[first] == priorList[second])
    {
        ++priorList[first];
    }
}


int main()
{
    int n, m, result = 0;
    int temp = INT_MAX;
    cin >> n >> m;

    vector<tuple<int, int, int>> graph(m);
    vector<vector<int>> adjList(n + 1, vector<int>(n + 1));
    vector<int> visList(n + 1);
    vector<int> priorList(n + 1);
    vector<int> edgeValues(n + 1);

    for (int i = 0; i <= n; ++i)
    {
        visList[i] = i;
        priorList[i] = 1;
    }

    for (int i = 0; i < m; ++i)
    {
        int first, second, weight;
        cin >> first >> second >> weight;

        graph[i] = make_tuple(first, second, weight);

        adjList[get<0>(graph[i])][get<1>(graph[i])] = get<2>(graph[i]);
        adjList[get<1>(graph[i])][get<0>(graph[i])] = get<2>(graph[i]);
    }

    sort(graph.begin(), graph.end(), comp);

    for (int i = 0; i < m; ++i)
    {
        int first = get<0>(graph[i]);
        int second = get<1>(graph[i]);
        int weight = get<2>(graph[i]);

        if (findParent(visList, first) == findParent(visList, second))
        {
            continue;
        }

        edgeValues[first] = max(edgeValues[first], weight);
        edgeValues[second] = max(edgeValues[second], weight);

        merge(visList, priorList, first, second);

        result += weight;
    }

    for (int i = 0; i <= n; ++i)
    {
        int value = edgeValues[i];
        int difference = INT_MAX;

        for (int j = 0; j < m; ++j)
        {
            int first = get<0>(graph[j]);
            int second = get<1>(graph[j]);
            int weight = get<2>(graph[j]);

            if (first != i && second != i || weight <= value)
            {
                continue;
            }

            difference = min(difference, weight - value);
        }

        temp = min(temp, difference);
    }

    cout << result << " " << result + temp;

    return 0;
}