
import java.util.HashMap;
import java.util.Map;

class Environment {
    Environment() {
        enclosing = null;
    }

    Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    Object get(Token name) {
        if (values.containsKey(name.lexeme)) {
            return values.get(name.lexeme);
        }

        if (this.enclosing != null) {
            return enclosing.get(name);
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    void assign(Token name, Object value) {
        if (values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }

        if (this.enclosing != null) {
            enclosing.assign(name, value);
            return;
        }

        throw new RuntimeError(name, "Undefined variable '" + name.lexeme + "'.");
    }

    void define(String name, Object value) {
        if (values.containsKey(name)) {
            throw new RuntimeError(null, "Variable '" + name + "' already defined.");
        }

        values.put(name, value);
    }
}