# BaseFilter<T> Package

This package provides a flexible and extensible framework for filtering collections of objects. It is built around a generic abstract class `BaseFilter<T>` that can be extended to create custom filtering logic, such as filtering based on a predefined list or a dynamic predicate.

---

## Key Components

The package consists of the following main classes:

### `BaseFilter<T>`

This is an **abstract class** that serves as the foundation for all 
filters. It defines the core filtering logic and requires subclasses to 
implement a single method: `check(T obj)`.

- `getPredicate()`: Returns a `Predicate<T>` that encapsulates the filter's logic.
- `filter(List<T> list)`: Filters a given list using the logic defined in `check(T obj)`.
- `abstract boolean check(T obj)`: The abstract method that must be implemented by concrete subclasses to define the specific filtering rule.

### `ListFiltering<T>`

This is a concrete implementation of `BaseFilter<T>`. It filters objects by checking if they are present in a **predefined list** provided during construction.

- **Constructor**: `public ListFiltering(List<T> validList)`
- **Purpose**: Ideal for scenarios where you need to filter a collection against a static list of valid items.

### `CachedFiltering<T>`

This is another concrete implementation of `BaseFilter<T>`. It filters objects by applying a **predicate** function. This is useful for more complex or dynamic filtering rules.

- **Constructor**: `public CachedFiltering(Predicate<T> validator)`
- **Purpose**: Perfect for situations where the filtering logic can be expressed as a `Predicate`, such as a lambda expression or a method reference.

---

## How to Use

### 1. Simple List-Based Filtering

To filter a list of objects against a static set of valid items, use `ListFiltering`.

```Java
import com.yourpackage.ListFiltering;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a list of all items
        List<String> allItems = Arrays.asList("apple", "banana", "cherry", "date");

        // Define a list of valid items
        List<String> validItems = Arrays.asList("apple", "cherry");

        // Create a ListFiltering instance
        ListFiltering<String> filter = new ListFiltering<>(validItems);

        // Filter the list
        List<String> filteredList = filter.filter(allItems);

        // Output: [apple, cherry]
        System.out.println(filteredList);
    }
}
```

### 2. Dynamic Predicate-Based Filtering
   To apply more dynamic or complex logic, use CachedFiltering with a Predicate.

```Java

import com.yourpackage.CachedFiltering;
import java.util.Arrays;
import java.util.List;

public class Main {
public static void main(String[] args) {
// Create a list of all items
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Create a filter for even numbers using a predicate
        CachedFiltering<Integer> evenNumberFilter = new CachedFiltering<>(number -> number % 2 == 0);

        // Filter the list
        List<Integer> evenNumbers = evenNumberFilter.filter(numbers);

        // Output: [2, 4, 6]
        System.out.println(evenNumbers);
    }
}
```

## Extending `BaseFilter<T>`
You can easily create your own custom filters by extending the BaseFilter class and implementing the check method.

For example, to create a filter that only keeps non-null objects:

```Java

import com.yourpackage.BaseFilter;

public class NonNullFilter<T> extends BaseFilter<T> {
@Override
protected boolean check(T obj) {
    return obj != null;
    }
}
```
Then, you can use your new filter just like the others:


```Java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> items = Arrays.asList("item1", null, "item2");
        NonNullFilter<String> filter = new NonNullFilter<>();
        List<String> filteredItems = filter.filter(items);

        // Output: [item1, item2]
        System.out.println(filteredItems);
    }

}
```