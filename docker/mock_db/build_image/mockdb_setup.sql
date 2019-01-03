/*
Erstellt in einer leeren postgis-aktivierten Datenbank die notwendigen
Datenbankobjekte, um die DB-Funktion entscheidung.entscheidung_detail(...)
zu mocken.
Datenbankobjekte:
- Schema entscheidung
- Typ entscheidung_resultat als "Zeilentyp". Rückgabetyp der Funktion
  entscheidung.entscheidung_detail(...). Wie bei Originalfunktion in Schema public.
- DB-Funktion entscheidung.entscheidung_detail(...)
*/
CREATE SCHEMA IF NOT EXISTS entscheidung;

DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM pg_type WHERE typname = 'entscheidung_resultat') THEN
		DROP TYPE entscheidung_resultat CASCADE;
    END IF;
END$$;

CREATE TYPE entscheidung_resultat AS
   (durchfuehrung_id integer,
    tiefe integer,
    resultat_gwszone boolean,
    resultat_gw_vorkommen boolean,
    resultat_quelle boolean,
    resultat_gwraum boolean,
    resultat_altlast boolean,
    resultat_rutsch boolean,
    resultat_gesamt boolean,
    text text);
   

CREATE OR REPLACE FUNCTION entscheidung.entscheidung_detail(
    p_x double precision,
    p_y double precision)
  RETURNS SETOF entscheidung_resultat AS
$BODY$
 
 declare ergebnis entscheidung_resultat;
 rec entscheidung_resultat%ROWTYPE;
 
 BEGIN
 
 	SELECT 99, NULL, TRUE, NULL, TRUE, TRUE, FALSE, NULL, FALSE, 
 	'Erste Zeile'||chr(10)||'Zweite Zeile'||chr(10)||chr(10)||'Dritte Zeile nach Leerzeile' INTO ergebnis;
	RETURN NEXT ergebnis;	
 
 END;
 $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
GRANT EXECUTE ON FUNCTION entscheidung.entscheidung_detail(double precision, double precision) TO public;
