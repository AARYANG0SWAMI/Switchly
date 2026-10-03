# Switchly

**A multi-tenant feature flag platform — built from scratch, one session at a time.**

Switchly lets other companies' applications turn features on and off remotely, without redeploying their code. It's the running project for a 25-session course on AI-first software engineering, and this repository grows as the course does.

> **Deploying code and releasing a feature are two different events. A feature flag is what separates them.**

---

## What it does

- **Two kinds of flags** — boolean (on/off) and dynamic config (any JSON value: banner text, a numeric limit).
- **Targeting** — percentage rollouts and user-attribute rules, with stable bucketing so the same user always gets the same answer.
- **Multi-tenancy** — unrelated companies sign up and each get their own isolated organizations → projects → environments (dev/staging/prod) → flags.
- **Two consoles, one app** — a Tenant Developer console for our customers, and an Owner console for the platform team, in a single React application.
- **Two kinds of auth** — email + password + JWT for humans; per-environment API keys for machines.
- **A client SDK** — a small JS/TS library that customer apps install to evaluate flags, with caching, polling and safe defaults.
- **A demo shop** — a separate toy app that signs up as a real tenant and visibly changes when a flag is flipped.
- **A small AI layer** — create flags from a sentence (with human confirmation), and search flags in plain English.
