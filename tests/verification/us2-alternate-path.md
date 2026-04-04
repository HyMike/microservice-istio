# US2 Alternate Path Verification

1. Start both services and frontend.
2. Submit 20 payloads with `routePreference: auto`.
3. Confirm responses include both `decisionSource=primary` and `decisionSource=alternate`.
4. Submit payload with `routePreference: alternate` and confirm alternate source is always returned.
5. Submit payload with `routePreference: primary` and confirm primary source is always returned.
