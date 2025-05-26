% search for an element in a list
search(E , [E | _]).
search(E , [_ | T]) :- search(E, T).


% search2 (Elem , List)
% looks for two consecutive occurrences of Elem
search2(E, [E , E | _]).
search2(E, [_ | T]):- search2(E, T).


% search_two (Elem , List )
% looks for two occurrences of Elem with one element in between
search_two(E, [E , _ , E | _]).
search_two(E, [_ | T]):- search_two(E, T).


% size (List , Size )
% Size will contain the number of elements in List
size([], zero).
size([H|T], s(N)):- size(T,N).


% sum (List ,Sum)
sum([], 0).
sum([H|T], S) :- sum(T, S2), S is H + S2.


% minmax(List, Max, Min)
% Suppose the list has at least one element
minmax([ Last ], Last, Last).
minmax([H | T], H, MAX) :- minmax(T , MIN, MAX), H < MIN.
minmax([H | T], MIN, H) :- minmax(T , MIN, MAX), H > MAX.
minmax([H | T], MIN, MAX) :- minmax(T , MIN, MAX), H >= MIN, H =< MAX.


% split (List1 , Elements , SubList1 , SubList2 )
% Splits a list into two sublists based on a given number of elements
split([], _, [], []).
split([ H ], N, [ H ], []) :- N < 0.
split([ H ], N, [],  [ H ]) :- N > 0.
split([H | T], N, [H | S1], S2) :- N > 0,  N1 is N - 1, split(T, N1, S1, S2).
split([H | T], N, S1, [H | S2]) :- N =< 0, split(T, N, S1, S2).


% rotate (List , RotatedList )
% Rotate a list , namely move the first element to the end of the list.
rotate([H | T], RL) :- append(T, [H], RL).


% count_occurrences ( Element , List , Count )
% Count is the number of times Element appears in List .
count_occurrences(E, [ E ], 1).
count_occurrences(E, [ _ ], 0).
count_occurrences(E, [E | T], C):- count_occurrences(E, T, C2), C is C2 +1.
count_occurrences(E, [_ | T], C):- count_occurrences(E, T, C).

