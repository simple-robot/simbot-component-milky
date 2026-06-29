# Module Milky model event data serializer resolver processor

Generates `milkyRawEventDataSerializersMap` and `resolveMilkyRawEventDataSerializer(eventType: String)`
for classes annotated with `MilkyRawEventDataMarker`.

Generates `milkyRawIncomingMessageSegmentSerializersMap` and
`resolveMilkyRawIncomingMessageSegmentSerializer(type: String)` for classes annotated with
`MilkyRawIncomingMessageSegmentTypeMarker`.
