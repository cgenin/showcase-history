# Show case history

## What I want to do ?

I want store history of `name` changes of `Country` entity. The old values must be store in `CountryHistoryEntity`

# How ?

On the entity we want to watch we add a Listener 

```java
@EntityListeners(CountrySpecificListener.class)
@Entity
public class Country {
```

The `CountrySpecificListener` is a listener only on the Country entity.

CountrySpecificListener has 2 puproses : 
- Store the initial value in a transient field of the current loaded entity.
```java
@PostLoad
    public void postLoad(Country country) {
        country.setInitialName(country.getName());
    }
```
- Send an synchronous event on postUpdate if there is a specific condition
```java
  @PostUpdate
  public void postUpdate(Country country) {
  var event = new CreateCountryHistoryEvent(country.getId(), country.getInitialName(), country.getName());
  publisher.publishEvent(event);
  }
```

The publisher is injected because this class is annotated by @Configurable

After the return of the controller, the spring event launched is consumed by the `CreateCountryHistoryEventListener`
This class just store the events in CountryHistoryEntity table

# Not perfect ?
- If there is no transaction, the number of events store is the number of the saved values.
- If there is a failure in listener, the controller has also a failure 