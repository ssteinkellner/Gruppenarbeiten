Gruppenarbeit PI Calculator
===========================

Gruppe:
- Steinkellner Sebastian
- Pöcher Rene

zusammenfassung Aufgabenstellung
--------------------------------

mithilfe des folgenden Java RMI interfaces soll in einem Netzwerk PI berechnet werden.

public interface Calculator {
  public BigDecimal pi (int anzahl nachkommastellen);
}

dabei sollen Client und Server zuerst direkt kommunizieren.
als erweiterung ist ein Loadbalancer zu implementieren.
