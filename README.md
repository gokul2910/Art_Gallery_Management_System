# Art Gallery Management System (Backend)

REST API for an art gallery app with JWT authentication and role-based access control (ADMIN / USER).

**Live demo:** https://art-gallery-frontend-one.vercel.app
**API base:** https://art-gallery-management-system-cq9x.onrender.com/api
**Frontend repo:** https://github.com/gokul2910/art-gallery-frontend

> Hosted on free tiers. The first request after inactivity can take up to a minute while the server wakes up.

## Features
- User registration and login with BCrypt password hashing
- Stateless JWT authentication with Spring Security filter
- Role-based authorization: anyone can view artworks, only ADMIN can add, update or delete
- Artwork and category management with Spring Data JPA
- CORS configured for the deployed frontend
- Dockerized and auto-deployed from GitHub

## Tech Stack
Java, Spring Boot, Spring Security, JWT, Spring Data JPA / Hibernate, MySQL, Maven, Docker
Deployment: Render (backend), Aiven (MySQL), Vercel (frontend)

## API Endpoints
| Method | Endpoint | Access |
|---|---|---|
| POST | /api/users/register | Public |
| POST | /api/users/login | Public |
| GET | /api/artworks, /api/artworks/{id} | Public |
| POST / PUT / DELETE | /api/artworks | ADMIN |
| GET | /api/categories | Public |
| POST / DELETE | /api/categories | ADMIN |
| GET | /api/users | ADMIN |

## Run locally
1. Clone the repo and create a MySQL database.
2. Create `src/main/resources/application-secrets.properties` (gitignored):
