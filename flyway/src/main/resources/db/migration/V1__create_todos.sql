-- V1__create_todos.sql
CREATE TABLE IF NOT EXISTS todos (
    id           SERIAL PRIMARY KEY,
    title        TEXT                     NOT NULL,
    description  TEXT,
    is_completed BOOLEAN                  NOT NULL DEFAULT FALSE,
    due_date     DATE,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- updated_at 自動更新
CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at := CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER todos_updated_at
BEFORE UPDATE ON todos
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();
