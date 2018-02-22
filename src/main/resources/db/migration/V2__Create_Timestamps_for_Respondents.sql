CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updatedt = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

ALTER TABLE respondents
  ADD COLUMN created TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  ADD COLUMN updated TIMESTAMPTZ NOT NULL DEFAULT NOW()
;

CREATE TRIGGER set_timestamp
BEFORE UPDATE ON respondents
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();
