# API Testing Guide (Postman)

This guide provides the necessary information to test the application's REST endpoints using Postman.

## Base URL
`http://localhost:8080`

---

## 1. Administrative Hierarchy (Rwanda)

### Provinces
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/provinces` | List all provinces |
| GET | `/api/provinces/{id}` | Get specific province |
| POST | `/api/provinces` | Create a new province |
| PUT | `/api/provinces/{id}` | Update a province |
| DELETE | `/api/provinces/{id}` | Delete a province |

**Sample POST/PUT Body:**
```json
{
  "name": "Kigali",
  "code": "1"
}
```

### Administrative Import
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/admin/locations/import` | Import full hierarchy from JSON |

**Sample Import Body:**
```json
[
  {
    "name": "Kigali",
    "code": "1",
    "districts": [
      {
        "name": "Nyarugenge",
        "code": "11",
        "sectors": [
          {
            "name": "Nyamirambo",
            "code": "1101",
            "cells": [
              {
                "name": "Rwezamenyo I",
                "code": "110101",
                "villages": [
                  { "name": "Village A", "code": "11010101" }
                ]
              }
            ]
          }
        ]
      }
    ]
  }
]
```

---

## 2. User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | List all users |
| GET | `/api/users/{id}` | Get user details |
| POST | `/api/users?villageIdentifier=11010101` | Create user (linked to village) |
| PUT | `/api/users/{id}?villageIdentifier=11010101` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

**Sample POST/PUT Body:**
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "securePassword123"
}
```

### Hierarchical User Search
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users/province/{identifier}` | Users in a province (ID or Code) |
| GET | `/api/users/district/{identifier}` | Users in a district |
| GET | `/api/users/sector/{identifier}` | Users in a sector |
| GET | `/api/users/cell/{identifier}` | Users in a cell |
| GET | `/api/users/village/{identifier}` | Users in a village |

---

## 3. Farm & Chicken Management

### Farms
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/farms` | List all farms |
| POST | `/api/farms` | Create a farm |

**Sample POST Body:**
```json
{
  "name": "Golden Feathers Farm",
  "owner": { "id": 1 },
  "location": { "address": "123 Poultry Lane", "province": { "id": 1 } }
}
```

### Chickens
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/chickens?page=0&size=10&sort=ageWeeks,desc` | Paginated chickens |
| GET | `/api/chickens/{id}` | Get chicken details |
| POST | `/api/chickens` | Add a chicken to a farm |

**Sample POST Body:**
```json
{
  "breed": "Leghorn",
  "hatchDate": "2024-01-15",
  "ageWeeks": 8,
  "weightKg": 1.2,
  "farm": { "id": 1 }
}
```

---

## 4. Roles
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/roles` | List all roles |
| POST | `/api/roles` | Create a role |

**Sample Body:**
```json
{
  "name": "ROLE_ADMIN"
}
```
