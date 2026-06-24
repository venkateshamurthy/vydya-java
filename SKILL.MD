---
name: java-spring-functional-architecture-master
description: Enterprise architectural playbook for Java 17, Spring Boot 3+, and a reactive/functional ecosystem. Enforces strict point-free styles, monadic pipelines (Try, Either, Validation), immutable DTOs, optimistic concurrency, and non-blocking Redis/MongoDB operations. Integrates Clean Architecture, DDD, observability, and Spring Cloud resilience. Automatically guards against SonarQube violations via ArchUnit rules.
---

# Java 17 & Spring Functional Architecture Master Playbook
---
# 🛠️ Coding Standards

## ✅ Null Safety and Monads
- **Prefer `java.util.Optional`** for null safety in domain models, service layers, and integration with Java APIs.
- **Use `io.vavr.control.Option`** only when:
    - Inside a Vavr monadic pipeline (`Try`, `Either`, `Validation`).
    - Functional composition features (`toTry`, pattern matching) are required.
- **Never mix `Optional` and `Option` in the same method** — pick one consistently per context.
- **ArchUnit Enforcement**:
    - Ban `Optional.get()` usage.
    - Allow `Option.getOrElse()` since it is safe.
- **Point‑free pipelines**: Always prefer method references and fluent monads over imperative branching.
- **Ban null‑guards**: Replace with `Optional.map()`, `Optional.flatMap()`, or safe fallbacks.

---

## 🚫 Collections
- **Do not use `io.vavr.collection.*`** (`List`, `Set`, `Map`).
- **Always use `java.util` collections** with immutability enforced:
    - `List.copyOf(...)`, `Set.copyOf(...)`, `Map.copyOf(...)`.
    - `Collectors.toUnmodifiableList()` / `Collectors.toUnmodifiableSet()`.
- **ArchUnit Enforcement**:
    - Flag any usage of `io.vavr.collection.*`.
    - Require `java.util` collections in all blueprints.

---

## 🔒 Collector Rules
- **Prefer `Collectors.toUnmodifiableSet()`** → immutability guaranteed; always prefer this in production pipelines.
- **Use `Collectors.toSet()`** → acceptable only if mutability is explicitly required.
- **Custom collectors** → always use concrete type references (`HashSet::addAll`) for combiners.
- **Never use raw types** like `Set::addAll` in collectors — they trigger type inference warnings.

---

## ⚡ Validation
- **Accumulative validation**: Use `Validation.combine(...).ap(...)` from Vavr.
- **Ban fail‑fast validation**: Always aggregate errors into immutable records or error lists.

---
## 🗄️ Data Access
- **Atomic MongoDB updates**:
    - Ban `findById().save()` patterns.
    - Require atomic updates via `updateFirst()` or `findAndModify()`.
    - Enforce optimistic concurrency with version fields.
- **Redis safety**:
    - Ban `redisTemplate.keys()`, `SMEMBERS`, `HGETALL`.
    - Require cursor‑driven scans (`SCAN`, `SSCAN`, `HSCAN`) with bounded iteration.

---

## 📦 DTOs
- **All DTOs must be Java Records**.
- No mutable setters or builders allowed.
- Immutability is mandatory.
---

## 🏛️ Architecture
- **Clean Architecture layering**:
    - Domain logic isolated from infrastructure.
    - No circular dependencies.
- **Observability enforced**:
    - Structured logging, metrics, tracing.
- **Security enforced**:
    - Input validation, authentication, authorization checks at boundaries.
---

## 📋 Checklist
- ✅ Use `Optional` for null safety.
- ⚡ Use `Option` only inside Vavr pipelines.
- 🚫 Ban `io.vavr.collection.*`.
- 🔒 Prefer immutable collectors (`toUnmodifiableSet`, `toUnmodifiableList`).
- 🛑 Disallow raw type combiners (`Set::addAll`).
- 🚨 Enforce point‑free pipelines, ban `.get()` and null‑guards.
- 🧩 Use accumulative validation with Vavr.
- 🗄️ Require atomic MongoDB updates with optimistic concurrency.
- 🔍 Use Redis SCAN instead of KEYS.
- 📦 DTOs must be Java Records.
- 🏛️ Follow Clean Architecture layering.
- 📊 Observability and security must be enforced.
---

---
## 🛠️ Technology Stack
* **Runtime:** Java 17 (Records, Switch Expressions, Pattern Matching, Text Blocks, Enhanced Streams)
* **Framework:** Spring Boot 3+ (WebFlux, Data MongoDB, Data Redis, Security, Spring Cloud)
* **Functional Controls:** io.vavr (Try, Either, Validation, Match, Tuples)
* **Reactive Layer:** Project Reactor (`Mono`, `Flux`)
* **Utilities:** Apache Commons, Google Guava
* **Data Stores:** MongoDB, Redis
* **Testing:** JUnit 5, AssertJ, Mockito
* **Verification:** ArchUnit validation suite
* **Observability:** Micrometer, Prometheus, Grafana, OpenZipkin/Jaeger
---
## 📋 Verification Checklist
- [ ] No `.isPresent()`, `.get()`, or null-guards.
- [ ] No `findById().save()` before update.
- [ ] No `redisTemplate.keys()`.
- [ ] All DTOs are Records.
- [ ] Clean Architecture layering enforced.
- [ ] Observability metrics configured.
- [ ] Security annotations present.
---

## 📤 Toolkit Output Format
Responses must include:
1. 🚨 Structural Assessment
2. 💡 Toolkit Blueprinting
3. 💻 Production Implementation
4. 🧪 Monadic Pipeline Test
---

## 📖 Master Reference Tool Kit Blueprints

### Blueprint 1: Composable Collection Drilling & Elvis Operators
```java
package com.project.toolkit.traversal;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Collections;

public class CollectionDrillingEngine {
    public record Image(String id, String url) {}
    public record StreamDocument(String streamId, List<Image> viewImages) {}
    public record Company(String orgId, List<StreamDocument> streams) {}

    public List<Image> getAllViewableImages(final Company company) {
        return Optional.ofNullable(company)
                .map(Company::streams)
                .map(CollectionUtils::emptyIfNull)
                .stream()
                .flatMap(List::stream)
                .flatMap(stream -> extractSafeImages(stream).stream())
                .toList();
    }

    private List<Image> extractSafeImages(final StreamDocument stream) {
        return Optional.ofNullable(stream)
                .map(StreamDocument::viewImages)
                .map(CollectionUtils::emptyIfNull)
                .stream()
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .toList();
    }
}

```
### Blueprint 2: Non-Blocking Redis Cursors Replacing

```java
package com.project.toolkit.redis;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FunctionalRedisScanner {
    private final StringRedisTemplate redisTemplate;

    public Set<String> scanKeysByPrefix(final String prefix) {
        final ScanOptions options = ScanOptions.scanOptions()
                .match(prefix + "*")
                .count(250)
                .build();

        return Optional.ofNullable(redisTemplate.execute(connection -> {
            try (Cursor<byte[]> cursor = connection.scan(options)) {
                return StreamSupport.stream(cursor.spliterator(), false)
                        .map(String::new)
                        .collect(Collectors.toUnmodifiableSet());
            } catch (Exception ex) {
                log.error("Redis SCAN iteration failed for prefix: {}", prefix, ex);
                return Collections.<String>emptySet();
            }
        })).orElse(Collections.emptySet());
    }
}
```
### Blueprint 3: Execute-Around Functional Distributed Lock Manager
```java
package com.project.toolkit.lock;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class FunctionalLockManager {
    private final StringRedisTemplate redisTemplate;

    public Try<?> executeWithLock(final String lockKey, final Duration ttl, final Supplier<?> criticalTask) {
        final String lockToken = UUID.randomUUID().toString();

        return tryAcquire(lockKey, lockToken, ttl).flatMap(acquired -> acquired
                ? executeAndRelease(lockKey, lockToken, criticalTask)
                : Try.failure(new IllegalStateException("Failed to acquire lock for key: " + lockKey)));
    }

    private Try<Boolean> tryAcquire(final String key, final String token, final Duration ttl) {
        return Try.of(() -> Optional.ofNullable(redisTemplate.opsForValue()
                        .setIfAbsent(key, token, ttl))
                .orElse(false));
    }

    private Try<?> executeAndRelease(final String key, final String token, final Supplier<?> task) {
        final Try<?> taskResult = Try.of(task::get);
        return tryRelease(key, token).flatMap(released -> taskResult);
    }

    private Try<Boolean> tryRelease(final String key, final String token) {
        private Try<Boolean> tryRelease(final String key, final String token) {
            final String luaScript =
                    "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                            "return redis.call('del', KEYS[1]) else return 0 end";

            return Try.of(() -> {
                Long result = redisTemplate.execute(
                        new org.springframework.data.redis.core.script.DefaultRedisScript<>(luaScript, Long.class),
                        java.util.List.of(key), token);

                // Explicitly check for non-null and equality
                return result != null && result.equals(1L);
            });
        }
    }
}
```
### Blueprint 4: Atomic Updater with Versioning (MongoDB Optimistic Concurrency)
```java
package com.project.toolkit.mongo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AtomicUpdater {
    private final MongoTemplate mongoTemplate;

    public Try<Boolean> incrementCounter(final String docId, final long expectedVersion) {
        final Query query = new Query(
                Criteria.where("id").is(docId)
                        .and("version").is(expectedVersion)
        );
        final Update update = new Update()
                .inc("counter", 1)
                .inc("version", 1);

        return Try.of(() -> Optional.ofNullable(
                        mongoTemplate.updateFirst(query, update, "documents"))
                .map(result -> result.getModifiedCount() == 1)
                .orElse(false));
    }
}
```
### Blueprint 5: Clean Architecture Example
```java
// Domain Layer
package com.project.domain;
public record Order(String id, String customerId, double amount) {}

// Application Layer
package com.project.application;
import com.project.domain.Order;
public interface OrderService {
    Order placeOrder(String customerId, double amount);
}

// Infrastructure Layer
package com.project.infrastructure;
import com.project.domain.Order;
import com.project.application.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Order placeOrder(String customerId, double amount) {
        return new Order("ORD-" + System.currentTimeMillis(), customerId, amount);
    }
}
```
### Blueprint 6: Reactive WebFlux Endpoint
```java
package com.project.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ReactiveController {
    @GetMapping("/numbers")
    public Flux<Integer> streamNumbers() {
        return Flux.range(1, 10).map(n -> n * 2);
    }
}
```
### Blueprint 7: ArchUnit Enforcement Suite
```java
package com.project.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

class ArchitectureRulesTest {
    private final JavaClasses importedClasses =
            new ClassFileImporter().importPackages("com.project");

    @Test void noOptionalGetCalls() {
        ArchRule rule = noClasses().should().callMethod("java.util.Optional", "get");
        rule.check(importedClasses);
    }

    @Test void noFindByIdThenSave() {
        ArchRule rule = noClasses().should().callMethod("org.springframework.data.repository.CrudRepository", "findById")
                .andShould().callMethod("org.springframework.data.repository.CrudRepository", "save");
        rule.check(importedClasses);
    }

    @Test void noRedisKeysUsage() {
        ArchRule rule = noClasses().should().callMethod("org.springframework.data.redis.core.RedisTemplate", "keys");
        rule.check(importedClasses);
    }

    @Test void dtoClassesMustBeRecords() {
        ArchRule rule = classes().that().resideInAPackage("..dto..")
                .should().beRecords();
        rule.check(importedClasses);
    }

    @Test void domainShouldNotDependOnInfrastructure() {
        ArchRule rule = classes().that().resideInAPackage("..domain..")
                .should().onlyDependOnClassesThat().resideOutsideOfPackage("..infrastructure..");
        rule.check(importedClasses);
    }

    @Test void observabilityMustUseMicrometer() {
        ArchRule rule = classes().that().resideInAPackage("..observability..")
                .should().dependOnClassesThat().resideInAPackage("io.micrometer..");
        rule.check(importedClasses);
    }

    @Test void serviceMethodsMustBeSecured() {
        ArchRule rule = methods().that().areDeclaredInClassesThat().resideInAPackage("..service..")
                .should().beAnnotatedWith("org.springframework.security.access.prepost.PreAuthorize");
        rule.check(importedClasses);
    }
}
```