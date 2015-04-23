package com.example;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class SimplePojo {
    @JsonProperty("name")
    private final String name;

    @JsonProperty("local")
    @JsonSerialize(using=LocalDateTimeSerializer.class, include=JsonSerialize.Inclusion.NON_NULL)
    @JsonDeserialize(using=LocalDateTimeDeserializer.class)
    private final LocalDateTime local;

    @JsonIgnore
    @JsonProperty("instant")
    @JsonSerialize(using=InstantSerializer.class, include=JsonSerialize.Inclusion.NON_NULL)
    @JsonDeserialize(using=InstantDeserializer.class)
    private final Instant instant;

    @JsonCreator
    public SimplePojo(@JsonProperty("name") String name,
                      @JsonProperty("local") LocalDateTime local,
                      @JsonProperty("instant") Instant instant) {
        this.name = name;
        this.local = local;
        this.instant = instant;
    }


    public Instant getInstant() {
        return instant;
    }

    public LocalDateTime getLocal() {
        return local;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final SimplePojo other = (SimplePojo) obj;
        return  Objects.equals(this.name, other.name) &&
                Objects.equals(this.instant, other.instant) &&
                Objects.equals(this.local, other.local);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Objects.hash(name, instant, local);
    }

    @Override
    public String toString() {
        return "SimplePojo{" +
                "name='" + name + '\'' +
                ", local=" + local +
                ", instant=" + instant +
                '}';
    }
}
