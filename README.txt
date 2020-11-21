
Ex1

this project contains 2 java classes implements the interfaces :node_info ,weighted_graph,weighted_graph_algorithms

###Node_Info Class:
--getNode--
returns a node from the graph that holds the given key.
--hasEdge--
return true if two given nodes have an edge between them
and false otherwise.
--getEdge--
returns the weight of the edge between two given nodes.
--addNode--
add a node to the graph.
--connect--
connect between two given vertexes with a given weight.
--getV--
returns a collection of all vertexes in the graph.
--get v()--
returns a collection of all the neighbors(that connected with an edge ) of a given vertex.
--removeNode--
removes a given vertex and all of his edges from the graph.
--removeEdge--
remove a given edge from the graph.


###WGraph_Algo Class:
--init--
this function allows as to initiate the graph 
(using a shallow pointer to point at a specific graph )
--getGraph--
return the graph
--copy--
this function allows us to copy a graph and returns the copy. 
--isConnected--
this boolean function returns true if all the vertex in the graph is connected and false otherwise(using the BFS algorithm ).
--shortestPathDist--
this function returns the shortest path from a source vertex to a destination vertex according to the weight of the edges. (using the Dijkstra algorithm)
--shortestPath--
this function returns the shortest path as a list of node_info.
(using the Dijkstra algorithm)
--save--
this function allows us to store a given situation of the graph in a file.
--load--
this function is to be used when we want to load the graph that we saved in the file.